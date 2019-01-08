package one.example.com.myapplication3.db.entity;

import one.example.com.myapplication3.db.dao.IFamily;

public class FamilyEtity implements IFamily {
    private int id;
    private int personId;
    private String text;
    private String age;

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
}
