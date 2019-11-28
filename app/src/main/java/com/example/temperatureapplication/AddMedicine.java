package com.example.temperatureapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMedicine extends AppCompatActivity {
    EditText ET_medicine, ET_dose, ET_time_of_day;
    String medicine, dose, time_of_day;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine);
        ET_medicine = findViewById(R.id.ETmedicine);
        ET_dose = findViewById(R.id.ETdose);
        ET_time_of_day = findViewById(R.id.ETtime_of_day);
    }
    public void SaveMedicine(View view){
        medicine = ET_medicine.getText().toString();
        dose = ET_dose.getText().toString();
        time_of_day = ET_time_of_day.getText().toString();
        String method = "saveMedicine";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, medicine, dose, time_of_day);
        finish();
    }
}
