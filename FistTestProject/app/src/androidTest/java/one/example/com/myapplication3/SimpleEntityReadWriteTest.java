package one.example.com.myapplication3;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.IOException;
import java.util.List;

import one.example.com.myapplication3.db.AppDatabase;
import one.example.com.myapplication3.db.User;
import one.example.com.myapplication3.db.dao.UserDao;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4.class)
 public class SimpleEntityReadWriteTest {
    private UserDao mUserDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mUserDao = mDb.userDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        User user = new User();
        user.setName("george");
        mUserDao.insertAll(user);
        List<User> list = mUserDao.getAll();
        for (int i = 0; i < list.size(); i++) {
            Logs.eprintln("User数据表遍历："+list.get(i).toString());
        }
//        MatcherAssert.assertThat(byName.get(0), equalTo(user));
    }
}
