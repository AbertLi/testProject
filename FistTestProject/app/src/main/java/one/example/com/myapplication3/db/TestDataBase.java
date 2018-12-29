package one.example.com.myapplication3.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import one.example.com.myapplication3.db.dao.TestDao;


@Database(entities = {Test.class}, version = 1,exportSchema = false)
public abstract class TestDataBase extends RoomDatabase {
    public abstract TestDao userDao();
}
