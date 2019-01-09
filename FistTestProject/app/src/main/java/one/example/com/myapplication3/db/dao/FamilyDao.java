package one.example.com.myapplication3.db.dao;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import one.example.com.myapplication3.db.DbConstant;
import one.example.com.myapplication3.db.entity.FamilyEntity;

@Dao
public interface FamilyDao {

    @Query("SELECT * FROM "+DbConstant.FAMILY_TABLE_NAME +" where personId = :personId")
    LiveData<List<FamilyEntity>> loadComments(int personId);

    @Query("SELECT * FROM "+DbConstant.FAMILY_TABLE_NAME +" where personId = :personId")
    List<FamilyEntity> loadCommentsSync(int personId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<FamilyEntity> comments);
}
