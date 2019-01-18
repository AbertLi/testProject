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

@Database(entities = {PersonEntity.class, FamilyEntity.class}, version = DbConstant.DB_VERSION)
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
                .addMigrations( MIGRATION_1_2 )
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


    //版本从1升级到2用到的。
    private static final Migration MIGRATION_1_2 = new Migration( DbConstant.DB_VERSION, DbConstant.DB_VERSION ) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL( "CREATE VIRTUAL TABLE IF NOT EXISTS `productsFts` USING FTS4(`name` TEXT, `description` TEXT, content=`products`)" );
            database.execSQL( "INSERT INTO productsFts (`rowid`, `name`, `description`)  SELECT `id`, `name`, `description` FROM products" );
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