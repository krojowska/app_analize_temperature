package com.example.temperatureapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class ButtonsPage extends AppCompatActivity {
    Button btnOneTemp, btnViewData, btnAllMedicines, btnDiagram, btnNotifications, btnAlarm, btnAllTemp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_page);

        // Buttons
        btnOneTemp = findViewById(R.id.btnOneTemp);
        btnAllMedicines = findViewById(R.id.btnAllMedicines);
        btnDiagram = findViewById(R.id.btnDiagram);
        btnNotifications = findViewById(R.id.btnNotifications);
        btnAlarm = findViewById(R.id.btnAlarm);
        btnAllTemp = findViewById(R.id.btnAllTemp);


       // startActivity(new Intent(ButtonsPage.this, GetAlarmTemperature.class));
        GetAlarmTemperature getTemp = new GetAlarmTemperature();
        getTemp.execute();
        String a = GetAlarmTemperature.alarmTemp;

        btnOneTemp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonsPage.this, MainActivity.class));
            }
        });
        btnAllTemp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonsPage.this, AllTemperatures.class));
            }
        });
        btnAllMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonsPage.this, AllMedicines.class));
            }
        });
        btnDiagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonsPage.this, ChartActivity.class);
                startActivity(intent);
            }
        });
        btnNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonsPage.this, MainNotifications.class));
            }
        });
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonsPage.this, SoundAlarm.class));
            }
        });
    }
}