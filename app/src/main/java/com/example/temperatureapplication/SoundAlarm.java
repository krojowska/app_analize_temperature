package com.example.temperatureapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SoundAlarm extends AppCompatActivity {

    //TextView alarmSetting, enterTemp;
        EditText tempEt;
        Button setBtn;
        String alarmTemp;
        TextView displayAlarmTemp;

    @Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_alarm);

//        alarmSetting =  findViewById(R.id.alarmSetting);
//        enterTemp =  findViewById(R.id.enterTemp);
        tempEt =  findViewById(R.id.tempEt);
        setBtn =  findViewById(R.id.setBtn);
        displayAlarmTemp = findViewById(R.id.displayAlarmTemp);

        displayAlarmTemp();

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

    public void displayAlarmTemp(){
        String alarmTempStr = GetAlarmTemperature.alarmTemp;
        displayAlarmTemp.setText(alarmTempStr);
    }

}
