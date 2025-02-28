package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DepressionQuestionrecommendationsActivity extends AppCompatActivity {

    Button btnActivities, btnHome;
    TextView txtRecommendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_depression_questionrecommendations);

        btnActivities = findViewById(R.id.btnActivities);
        btnHome = findViewById(R.id.btnHome);
        txtRecommendation = findViewById(R.id.txtRecommendation);

        //store the recommendations to the shared preferences
        SharedPreferences recommendations = getSharedPreferences("Recommendation", MODE_PRIVATE);
        SharedPreferences.Editor editor = recommendations.edit();

        // retrieve the fullMarks
        SharedPreferences sharedPreferencesMarks = getSharedPreferences("Depression_Marks", MODE_PRIVATE);
        int totalMarks = sharedPreferencesMarks.getInt("Depression_TotalMarks", 0);

        if (totalMarks <= 4 || totalMarks == 0) {
            txtRecommendation.setText("You're doing well! Remember to maintain healthy habits such as regular exercise, a balanced diet, and spending time on activities that bring you joy.");
        } else if (totalMarks <= 9 || totalMarks == 5) {
            txtRecommendation.setText("Try engaging in stress-relieving activities like meditation, journaling, or talking to a trusted friend. Keep monitoring your mood, and don't hesitate to explore the self-help activities available in this app.");
        } else if (totalMarks <= 14 || totalMarks == 10) {
            txtRecommendation.setText("Consider scheduling a conversation with a university counselor to gain professional insights. Additionally, explore the self-help resources in this app, such as guided activities for emotional well-being.");
        } else if (totalMarks <= 19 || totalMarks == 15) {
            txtRecommendation.setText("It’s highly recommended that you speak with a university counselor. You can chat with a counselor directly through this mobile application or call the 1333 hotline, a 24/7 service integrated into the app.");
        } else if (totalMarks <= 27 || totalMarks == 20) {
            txtRecommendation.setText("Seeking immediate professional support is crucial. Please connect with a university counselor via the chat option in this mobile application. Alternatively, call the 1333 hotline, a 24/7 service available to support you anytime.");
        }

        // save and apply the marks
        editor.putString("Depression_Recommendation", txtRecommendation.getText().toString());
        editor.apply();

        //call to the functions
        gotoActivities();
        gotoHome();
    }

    public void gotoActivities(){
        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepressionQuestionrecommendationsActivity.this, ActivitiesActivity.class);
                startActivity(intent);
            }
        });
    }

    public  void gotoHome(){
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepressionQuestionrecommendationsActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}