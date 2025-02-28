package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PredictionActivity extends AppCompatActivity {

    // initialization
    TextView txtRecommendation;
    Button btnActivities, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_prediction);

        // References
        txtRecommendation = findViewById(R.id.txtRecommendation);
        btnActivities = findViewById(R.id.btnActivities);
        btnHome = findViewById(R.id.btnHome);

        //store the recommendations to the shared preferences
        SharedPreferences recommendations = getSharedPreferences("Recommendation", MODE_PRIVATE);
        SharedPreferences.Editor editor = recommendations.edit();

        // retrieve the marks from the shared preferences
        SharedPreferences sharedPreferencesMarks = getSharedPreferences("User_Marks", MODE_PRIVATE);
        int fullMarks = sharedPreferencesMarks.getInt("Full_Marks", 0);

        if (fullMarks <= 4 || fullMarks == 0) {
            txtRecommendation.setText("You're doing great! Keep up your positive habits like regular exercise, good sleep, and doing things that make you happy.");
        } else if (fullMarks <= 9 || fullMarks == 5) {
            txtRecommendation.setText("Try simple stress-busters like deep breathing, listening to music, or taking short walks. Small changes in your routine can help!");
        } else if (fullMarks <= 14 || fullMarks == 10) {
            txtRecommendation.setText("Consider mindfulness exercises, journaling your thoughts, or talking to a close friend. Prioritize self-care and balance your workload.");
        } else if (fullMarks <= 21 || fullMarks == 15) {
            txtRecommendation.setText("It's important to seek support. Try relaxation techniques, but don’t hesitate to meet the university counselor for further guidance and professional help!");
        }


        // save and apply the marks
        editor.putString("Anxiety_Recommendation", txtRecommendation.getText().toString());
        editor.apply();

        gotoActivities();
        gotoHome();

    }

    public void gotoActivities(){
        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PredictionActivity.this, ActivitiesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void gotoHome(){
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PredictionActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

}