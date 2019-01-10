package one.example.com.myapplication3.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import one.example.com.myapplication3.db.DbConstant;
import one.example.com.myapplication3.modle.IPersonBean;
@Entity(tableName = DbConstant.PERSON_TABLE_NAME)
public class PersonEntity implements IPersonBean {
    @PrimaryKey
    private int id;
    private String name;
    private String age;

    public PersonEntity(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
