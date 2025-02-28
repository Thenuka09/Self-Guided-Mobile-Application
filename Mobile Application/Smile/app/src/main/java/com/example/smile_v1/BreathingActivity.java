package com.example.smile_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BreathingActivity extends AppCompatActivity {

    Button gotoActivitiesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_breathing);

        gotoActivitiesBtn = findViewById(R.id.gotoActivitiesBtn);

        //call to the functions
        gotoActivities();
    }

    public void gotoActivities(){
        gotoActivitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreathingActivity.this, ActivitiesActivity.class);
                startActivity(intent);
            }
        });
    }
}