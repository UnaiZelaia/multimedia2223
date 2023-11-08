package com.example.masterdetailexercise;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChampionsDAO {

    @Query("SELECT * FROM champion")
    LiveData<List<Champion>> getChampions();

    @Insert
    void insert(Champion champion);

    @Update
    void update(Champion champion);

    @Delete
    void delete(Champion champion);

}
