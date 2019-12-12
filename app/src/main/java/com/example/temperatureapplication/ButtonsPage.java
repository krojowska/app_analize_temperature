package com.example.temperatureapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class ButtonsPage extends AppCompatActivity {
    Button btnViewData, btnAllMedicines, btnAddMedicine, btnDiagram, btnNotifications;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_page);

        // Buttons
        btnViewData = findViewById(R.id.btnViewData);
        btnAllMedicines = findViewById(R.id.btnAllMedicines);
        btnDiagram = findViewById(R.id.btnDiagram);
        btnAddMedicine = findViewById(R.id.btnAddMedicine);
        btnNotifications = findViewById(R.id.btnNotifications);

        // view products click event
        btnViewData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonsPage.this, MainActivity.class));
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

        btnAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonsPage.this, AddMedicine.class));
            }
        });

        btnNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonsPage.this, MainNotifications.class));
            }
        });

    }
}