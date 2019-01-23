package one.example.com.myapplication3.db.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;
import one.example.com.myapplication3.db.entity.AdEntity;
import one.example.com.myapplication3.db.entity.User;

@Dao
public interface AdDao {
    @Insert
    void insertAll(User... users);

//    @Query("SELECT * FROM AdEntity")
//    Single<List<AdEntity>> getAdRxjava();

    @Query("SELECT * FROM AdEntity")
    List<AdEntity> getAll();

    @Query("SELECT * FROM AdEntity")
    LiveData<List<AdEntity>> getAllLivedata();
}
