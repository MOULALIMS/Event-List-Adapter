package com.example.revatrail2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectionActivity extends AppCompatActivity {

    Button addtotech,addtosport,addtocult;
    TextView exit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        addtotech =findViewById(R.id.redirecttoTechnical);
        addtosport =findViewById(R.id.redirecttoSport);
        addtocult = findViewById(R.id.redirecttoCultural);
        exit = findViewById(R.id.backtomainpage);

        addtotech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this,AddingTechnicalActivity.class);
                startActivity(intent);
            }
        });

        addtosport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this,AddingSportsActivity.class);
                startActivity(intent);
            }
        });

        addtocult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this,AddingCulturalActivity.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this,MainActivity.class);
                startActivity(intent);
                return;
            }
        });
    }
}