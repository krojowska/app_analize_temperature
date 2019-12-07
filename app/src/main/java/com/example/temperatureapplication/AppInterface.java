package com.example.temperatureapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppInterface {
    @GET("test3.php")
    Call<List<Growth>> getGrowthInfo();
}
