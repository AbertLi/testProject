package one.example.com.myapplication3.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

import one.example.com.myapplication3.db.Test;

@Dao
public interface TestDao {
    @Query("SELECT * FROM test")
    List<Test> getAll();
    @Query("SELECT * FROM test WHERE id IN (:testIds)")
    List<Test> loadAllByIds(int[] testIds);

    @Query("SELECT * FROM test WHERE message LIKE :message")
    Test findByMessage(String message);

    @Insert
    void insertAll(Test... tests);

    @Delete
    void delete(Test test);
}