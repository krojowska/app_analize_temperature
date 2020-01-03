package com.example.temperatureapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SoundAlarm extends AppCompatActivity {

    //TextView alarmSetting, enterTemp;
    EditText tempEt;
        Button setBtn;

        String alarmTemp;

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_alarm);

//        alarmSetting =  findViewById(R.id.alarmSetting);
//        enterTemp =  findViewById(R.id.enterTemp);
        tempEt =  findViewById(R.id.tempEt);
        setBtn =  findViewById(R.id.setBtn);

        }

public void addAlarm(View view){
        alarmTemp = tempEt.getText().toString();
        if (alarmTemp.isEmpty()) {
        Toast.makeText(SoundAlarm.this, "Please enter temperature", Toast.LENGTH_SHORT).show();
        }
        else {
        String method = "addAlarm";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, alarmTemp);
        }
        }
        }
