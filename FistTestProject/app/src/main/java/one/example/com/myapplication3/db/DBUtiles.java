package one.example.com.myapplication3.db;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DBUtiles {
    private static AppDatabase db = null;

    public static AppDatabase getAppDatabase(Context context) {
        if (db != null) {
            synchronized (DBUtiles.class) {
                db = Room.databaseBuilder(context, AppDatabase.class, "database-name").build();
            }
        }
        return db;
    }
}
