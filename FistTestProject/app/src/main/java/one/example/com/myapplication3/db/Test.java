package one.example.com.myapplication3.db;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "test")
public class Test {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "message")
    private String message;

    public void setMessage(String message){
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }
}
