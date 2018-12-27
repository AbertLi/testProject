package one.example.com.myapplication3.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//   https://www.jianshu.com/p/29e5e8c75450
//   https://www.jianshu.com/p/5ba249216e83
@Entity
public class User {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    public User() {
    }

    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}