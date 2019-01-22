package one.example.com.myapplication3.db.dao;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import one.example.com.myapplication3.db.DbConstant;
import one.example.com.myapplication3.db.entity.FamilyEntity;
import one.example.com.myapplication3.db.entity.PersonEntity;

@Dao
public interface FamilyDao {

    @Query("SELECT * FROM "+DbConstant.FAMILY_TABLE_NAME )
    LiveData<List<FamilyEntity>> loadFamilys();

    @Query("SELECT * FROM "+DbConstant.FAMILY_TABLE_NAME )
    List<FamilyEntity> loadFamilysList();

    @Query("SELECT * FROM "+DbConstant.FAMILY_TABLE_NAME +" where personId = :personId")
    LiveData<List<FamilyEntity>> loadFamilyByPersonId(int personId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<FamilyEntity> family);

}
