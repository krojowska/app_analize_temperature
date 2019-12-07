package com.example.temperatureapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        ChartFragment chartFragment = new ChartFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, chartFragment).commit();
    }
}
