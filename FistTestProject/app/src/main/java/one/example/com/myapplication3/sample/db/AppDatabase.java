package one.example.com.myapplication3.sample.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import one.example.com.myapplication3.sample.SampleApplication;
import one.example.com.myapplication3.sample.main.db.Subject;
import one.example.com.myapplication3.sample.main.db.SubjectDao;

@Database(entities = {Subject.class}, version = 2, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase INSTANCE;

    public static AppDatabase getInstance() {
        synchronized (AppDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(SampleApplication.APP, AppDatabase.class, "sample.db").build();
            }
            return INSTANCE;
        }
    }

    public abstract SubjectDao subjectDao();

//  以下方式已经被证明无效，请勿尝试类似实现
//        private LruCache<Class<?>, ?> mDaos = new LruCache<Class<?>, Object>(Integer
//            .MAX_VALUE) {
//        @Override
//        protected Object create(Class key) {
//            return createDao(key);
//        }
//    };
//
//    /**
//     * 根据类名获取Dao
//     */
//    public <T> T getDao(Class<T> clz) {
//        return clz.cast(mDaos.get(clz));
//    }
//
//    /**
//     * 创建dao
//     */
//    private Object createDao(Class clz) {
//        try {
//            String implClzName = clz.getName() + "_Impl";
//            Class<?> aClass = Class.forName(implClzName);
//            return aClass.getConstructor(RoomDatabase.class).newInstance(this);
//        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
//            throw new IllegalArgumentException("class " + clz.getName() + " may not Dao class", e);
//        }
//    }
}