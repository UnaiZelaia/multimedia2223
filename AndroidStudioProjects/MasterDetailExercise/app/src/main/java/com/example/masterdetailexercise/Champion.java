package com.example.masterdetailexercise;

import java.util.ArrayList;

public class Champion {

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
