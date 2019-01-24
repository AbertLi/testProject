package one.example.com.myapplication3.db.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;
import one.example.com.myapplication3.db.entity.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllLiveData();

//    @Query("SELECT * FROM user")//1，猜想当User的数据表已经建好的时候，single和LiveData两种数据持有方式同时存在的时候编译就不能通过了 推翻：单独使用single持有也不行。
//    Single<List<User>> getAllRxJava();//2，有可能是因为room数据库用的是androidx.room包下面的类，所有不能使用Single持有数据

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}