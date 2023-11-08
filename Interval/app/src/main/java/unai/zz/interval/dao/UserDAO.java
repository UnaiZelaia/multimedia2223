package unai.zz.interval.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import unai.zz.interval.model.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM user WHERE username = :user")
    LiveData<User> getUser(String user);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
