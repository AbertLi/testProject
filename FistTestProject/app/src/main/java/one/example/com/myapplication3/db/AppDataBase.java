package one.example.com.myapplication3.db;

import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import one.example.com.myapplication3.AppExecutors;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.db.dao.FamilyDao;
import one.example.com.myapplication3.db.dao.PersonDao;
import one.example.com.myapplication3.db.entity.FamilyEntity;
import one.example.com.myapplication3.db.entity.PersonEntity;

/**
 * 1, insert方法里面 @Insert(onConflict = OnConflictStrategy.REPLACE) 的REPLACE表示替换原有的单项数据。（主键默认不是自增长，不知道是否可以让他进行自增长）。
 * 2,关于数据库升级的问题。(添加数据表，更改表名，增改删加表字段)
 * 3,在项目中不能出现多个RoomDatabase的实现类。否则编译不能通过。
 */
@Database(entities = {PersonEntity.class, FamilyEntity.class}, version = DbConstant.DB_VERSION_5)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase mFistProjectDataBase;

    public static AppDataBase getInstance(Context context, final AppExecutors executors) {
        if (mFistProjectDataBase == null) {
            synchronized (AppDataBase.class) {
                if (mFistProjectDataBase == null) {
                    mFistProjectDataBase = buildDatabase( context, executors );
                    mFistProjectDataBase.updateDatabaseCreated( context.getApplicationContext() );
                }
            }
        }
        return mFistProjectDataBase;
    }

    private static AppDataBase buildDatabase(final Context appContext, final AppExecutors executors) {
        return Room.databaseBuilder( appContext, AppDataBase.class, DbConstant.DATABASE_NAME )
                .addCallback( new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate( db );
                        executors.diskIO().execute( () -> {
                            // Add fullScreenNotification delay to simulate fullScreenNotification long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            AppDataBase database = AppDataBase.getInstance( appContext, executors );
                            List<PersonEntity> person = DataGenerator.generatePersons();
                            List<FamilyEntity> family = DataGenerator.generateFamilyForPersons( person );
                            // notify that the database was created and it's ready to be used
                            insertData( database, person, family );
                            database.setDatabaseCreated();
                        } );
                    }
                } )
                .addMigrations( MIGRATION_1_2 )//这种方法可以添加字段，修改表名等，比较常用。
                .addMigrations( MIGRATION_2_3 )
                .addMigrations( MIGRATION_3_4)
                .addMigrations( MIGRATION_4_5)
//                .fallbackToDestructiveMigration()//如果更新新数据库,则丢弃原来的表
                .allowMainThreadQueries()//表示可以在主线程访问
                .build();
    }

    private static void addDelay() {
        try {
            Thread.sleep( 6000 );
        } catch (InterruptedException ignored) {
        }
    }

    public abstract PersonDao personDao();

    public abstract FamilyDao familyDao();


    private static void insertData(final AppDataBase database, final List<PersonEntity> person, final List<FamilyEntity> family) {
        database.runInTransaction( () -> {
            Logs.iprintln( "generator person data=" + person.toString() + " length=" + person.size() + "   generator family data=" + family.toString() + "   ;length=" + family.size() );
            database.personDao().insertAll( person );
            database.familyDao().insertAll( family );
        } );
    }


    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath( DbConstant.DATABASE_NAME ).exists()) {
            setDatabaseCreated();
        }
    }

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue( true );
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }


    //版本从1升级到2用到的。添加User表
    private static final Migration MIGRATION_1_2 = new Migration( DbConstant.DB_VERSION_1, DbConstant.DB_VERSION_2 ) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL( "DROP TABLE User" );
            database.execSQL( "CREATE  TABLE IF NOT EXISTS 'User'(`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `first_name` TEXT, `last_name` TEXT ,`age` TEXT)" );//添加User表，里面的字段必须和User实体类里面的字段一致。
//            database.execSQL( "INSERT INTO User (1, `name`, `description`)  SELECT `id`, `name`, `description` FROM products" );
        }
    };

    //版本从2升级到3用到的。User表里面添加age字段   这种方式以后问题！！！
    private static final Migration MIGRATION_2_3 = new Migration( DbConstant.DB_VERSION_2, DbConstant.DB_VERSION_3 ) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL( "ALTER TABLE User ADD COLUMN 'age' TEXT" );//添加User表，里面的字段必须和User实体类里面的字段一致。
        }
    };


    //在user表里面增加sex字段
    private static final Migration MIGRATION_3_4 = new Migration( DbConstant.DB_VERSION_3, DbConstant.DB_VERSION_4 ) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL( "ALTER TABLE User ADD COLUMN 'age' TEXT" );//添加User表，里面的字段必须和User实体类里面的字段一致。
            database.execSQL("ALTER TABLE User ADD COLUMN sex TEXT");
        }
    };

    //删除数据库里面的User表
    private static final Migration MIGRATION_4_5 = new Migration( DbConstant.DB_VERSION_4, DbConstant.DB_VERSION_5 ) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE User");
        }
    };

}


//如果该类不为抽象类就需要实现以下方法
//    @NonNull
//    @Override
//    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    protected InvalidationTracker createInvalidationTracker() {
//        return null;
//    }
//
//    @Override
//    public void clearAllTables() {
//
//    }




//  bug解决方法如下  Android:android.database.sqlite.SQLiteConstraintException:UNIQUE constraint failed
//
//可能发生这种BUG的两种情况
//        1：定义的字段为NOT　NULL，而插入时对应的字段为NULL
//        2：你定义的自动为PRIMARY，而插入时想插入的值已经在表中存在。
//
//        针对情况2，建议用replace代替insert
//        详细介绍查看博客：Android:Sqlite插入或更新–replace
