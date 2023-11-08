package unai.zz.interval.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import unai.zz.interval.model.Interval;

@Dao
public interface IntervalDAO {

    @Query("SELECT * FROM interval WHERE id_user = :userId")
    LiveData<List<Interval>> getUserIntervals(int userId);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Interval interval);
    @Update
    void update(Interval interval);
    @Delete
    void delete(Interval interval);
}
