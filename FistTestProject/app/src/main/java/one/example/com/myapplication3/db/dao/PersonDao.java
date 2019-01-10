package one.example.com.myapplication3.db.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import one.example.com.myapplication3.db.DbConstant;
import one.example.com.myapplication3.db.entity.PersonEntity;
@Dao
public interface PersonDao {
    @Query("SELECT * FROM " + DbConstant.PERSON_TABLE_NAME)
    LiveData<List<PersonEntity>> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PersonEntity> products);

    @Query("select * from " + DbConstant.PERSON_TABLE_NAME+" where id = :productId")
    LiveData<PersonEntity> loadProduct(int productId);

    @Query("select * from " + DbConstant.PERSON_TABLE_NAME+" where id = :productId")
    PersonEntity loadProductSync(int productId);

    @Query("SELECT * FROM "+ DbConstant.PERSON_TABLE_NAME+" WHERE name Like :query")
    LiveData<List<PersonEntity>> searchAllProducts(String query);
}
