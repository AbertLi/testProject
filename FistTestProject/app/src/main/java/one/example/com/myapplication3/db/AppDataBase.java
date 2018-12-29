package one.example.com.myapplication3.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import one.example.com.myapplication3.db.dao.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}