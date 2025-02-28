package com.example.smile_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ActivitiesActivity extends AppCompatActivity {

    // initialization
    ConstraintLayout backBtn, game, meditation, breething;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_activities);

        // referencing
        backBtn = findViewById(R.id.backBtn);
        game = findViewById(R.id.game);
        meditation = findViewById(R.id.meditation);
        breething = findViewById(R.id.breething);

        // call the methods
        goBack();
        playGame();
        goMeditation();
        gotoBreathing();

    }

    public void goBack(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitiesActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    public void playGame(){
        game.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivitiesActivity.this, SplashScreen.class);
                startActivity(intent);
            }
        });
    }

    public void goMeditation(){
        meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitiesActivity.this, MeditationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void gotoBreathing(){
        breething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitiesActivity.this, BreathingActivity.class);
                startActivity(intent);
            }
        });
    }
}