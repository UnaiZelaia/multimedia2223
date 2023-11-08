package eus.urko.roomAriketa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ElementsDAO {

    @Query("SELECT * FROM element")
    LiveData<List<Element>> getElements();

    @Insert
    void insert(Element element);

    @Update
    void update(Element element);

    @Delete
    void delete(Element element);
}
