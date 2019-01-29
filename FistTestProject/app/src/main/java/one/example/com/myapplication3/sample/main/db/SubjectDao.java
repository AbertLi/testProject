package one.example.com.myapplication3.sample.main.db;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * 命名规范：业务逻辑名+Dao 如SubjectDao
 * Entity命名规范：业务逻辑名+Entity 如SubjectEntity
 */
@Dao
public interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSubjects(List<Subject> subjects);

    @Query("DELETE FROM Subject")
    public void nukeTable();

    /**
     * 如果结果为空则直接complete
     */
    @Query("SELECT * FROM Subject WHERE id = :subjectId")
    public Maybe<Subject> getSubject(int subjectId);

    /**
     * 如果为空则抛异常
     */
    @Query("SELECT * FROM Subject WHERE id = :subjectId")
    public Single<Subject> getSubject1(int subjectId);

    /**
     * 建议用single，因为maybe也不会返回空数据
     */
    @Query("SELECT * FROM Subject")
    public Single<List<Subject>> getAll();

}