package com.example.temperatureapplication;

import com.google.gson.annotations.SerializedName;

public class Growth {
    @SerializedName("sensorsTemperature")
    private float Temperature;

    @SerializedName("id")
    private int Id;

    public float getTemperature() {
        return Temperature;
    }

    public int getId() {
        return Id;
    }
}
