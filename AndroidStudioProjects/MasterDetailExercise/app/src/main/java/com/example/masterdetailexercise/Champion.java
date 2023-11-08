package com.example.masterdetailexercise;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "champion")
public class Champion {
    @PrimaryKey(autoGenerate = true)
            int id;

    String name;
    String position;
    String region;
    float winRate;
    int image;
    String description;

    public Champion(int image, String name, String position, String region, float winRate, String description){
        this.image = image;
        this.name = name;
        this.position = position;
        this.region = region;
        this.winRate = winRate;
        this.description = description;
    }
}
