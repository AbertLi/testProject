//package one.example.com.myapplication3.db;
//
//import android.content.Context;
//
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.MutableLiveData;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//import one.example.com.myapplication3.AppExecutors;
//import one.example.com.myapplication3.db.entity.FamilyEtity;
//import one.example.com.myapplication3.db.entity.PersonBean;
//
//public abstract class FistProjectDataBase extends RoomDatabase {
//    public static final String DATABASE_NAME = "albert-sample-db";
//
//
//    private static FistProjectDataBase mFistProjectDataBase;
//
//    public static FistProjectDataBase getInstance(Context context, final AppExecutors executors) {
//        if (mFistProjectDataBase == null) {
//            synchronized (FistProjectDataBase.class) {
//                if (mFistProjectDataBase == null) {
//                    mFistProjectDataBase = buildDatabase( context, executors );
//                }
//            }
//        }
//        return mFistProjectDataBase;
//    }
//
//    /**
//     * Build the database. {@link Builder#build()} only sets up the database configuration and
//     * creates a new instance of the database.
//     * The SQLite database is only created when it's accessed for the first time.
//     */
//    private static FistProjectDataBase buildDatabase(final Context appContext, final AppExecutors executors) {
//        return Room.databaseBuilder( appContext, FistProjectDataBase.class, DATABASE_NAME )
//                .addCallback( new Callback() {
//                    @Override
//                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                        super.onCreate( db );
//                        executors.diskIO().execute( () -> {
//                            // Add a delay to simulate a long-running operation
//                            addDelay();
//                            // Generate the data for pre-population
//                            FistProjectDataBase database = FistProjectDataBase.getInstance( appContext, executors );
//                            List<PersonBean> products = DataGenerator.generatePersons();
//                            List<FamilyEtity> comments = DataGenerator.generateFamilyForPersons( products );
//                            insertData( database, products, comments );
//                            // notify that the database was created and it's ready to be used
//                            database.setDatabaseCreated();
//                        } );
//                    }
//                } )
//                .addMigrations( MIGRATION_1_2 )
//                .build();
//    }
//
//
//    /**
//     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
//     */
//    private void updateDatabaseCreated(final Context context) {
//        if (context.getDatabasePath(DATABASE_NAME).exists()) {
//            setDatabaseCreated();
//        }
//    }
//
//    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
//    private void setDatabaseCreated(){
//        mIsDatabaseCreated.postValue(true);
//    }
//
//    //如果该类不为抽象类就需要实现以下方法
////    @NonNull
////    @Override
////    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
////        return null;
////    }
////
////    @NonNull
////    @Override
////    protected InvalidationTracker createInvalidationTracker() {
////        return null;
////    }
////
////    @Override
////    public void clearAllTables() {
////
////    }
//}
