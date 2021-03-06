package one.example.com.myapplication3.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import one.example.com.myapplication3.db.DbConstant;
import one.example.com.myapplication3.modle.IFamily;

@Entity(tableName = DbConstant.FAMILY_TABLE_NAME)
public class FamilyEntity implements IFamily {
    @PrimaryKey
    private int id;
    private int personId;
    private String text;
    private String age;
    private String like;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getAge() {
        return age;
    }

    @Override
    public String getLike() {
        return like;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
