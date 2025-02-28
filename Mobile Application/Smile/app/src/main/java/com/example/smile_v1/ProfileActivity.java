package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.dmoral.toasty.Toasty;

public class ProfileActivity extends AppCompatActivity {

    // initialization
    TextView tv_email, tv_progress, tv_recommendation;

    ImageView btnHome;

    Button btnLogout;

    private int previousMarksPre;
    private int newMarkPre;

    private int presentage;
    private String progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        // referencing

        tv_email = findViewById(R.id.email);
        btnHome = findViewById(R.id.btnHome);
        tv_progress = findViewById(R.id.tv_progress);
        tv_recommendation = findViewById(R.id.tv_recommendation);
        btnLogout = findViewById(R.id.btnLogout);
//        tv_email = findViewById(R.id.email);
//        tv_faculty = findViewById(R.id.faculty);
//        tv_student_id = findViewById(R.id.student_id);
//        tv_phone_number = findViewById(R.id.phone_number);
//        marks = findViewById(R.id.marks);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "N/A");
        String email = sharedPreferences.getString("email", "N/A");
        String faculty = sharedPreferences.getString("faculty", "N/A");
        String student_id = sharedPreferences.getString("student_id", "N/A");
        String phone_number = sharedPreferences.getString("phone_number", "N/A");
        int previousMarks = sharedPreferences.getInt("previousMarks",0);

        // retrieve the total marks
//        SharedPreferences sharedPreferencesTotalMarks = getSharedPreferences("User_Marks",MODE_PRIVATE);
//        int totalMarks = sharedPreferencesTotalMarks.getInt("Full_Marks",0);

        // test the marks
//        Log.d("previousMarks",String.valueOf(previousMarks));
//        Log.d("Full_Marks",String.valueOf(totalMarks));

        //retrieve the recommendations
        SharedPreferences sharedPreferencesRecommendation = getSharedPreferences("Recommendation",MODE_PRIVATE);
        String anx_recommendation = sharedPreferencesRecommendation.getString("Anxiety_Recommendation", "First, you need to complete the Anxiety Test.");
        String dep_recommendation = sharedPreferencesRecommendation.getString("Depression_Recommendation", "First, you need to complete the Depression Test.");

        // calculate the progress
//        previousMarksPre = (int) ((double) previousMarks / 21 * 100);
//
//        newMarkPre = (int) ((double) totalMarks / 21 * 100);

        // write the condition
//        if (previousMarks == 0 && totalMarks ==0){
//            progressText = "Before making progress, you need to complete the Anxiety Test.";
//        } else if (totalMarks == 0 && totalMarks<previousMarks) {
//            progressText = "You are in very good mental health.";
//        } else if (previousMarks < totalMarks && !(previousMarks == 0)) {
//            presentage = newMarkPre - previousMarksPre;
//            progressText = "Your anxiety level has increased by " +presentage+"% compared to your previous assessment.";
//        } else if (previousMarks > totalMarks && !(totalMarks == 0)) {
//            presentage = previousMarksPre - newMarkPre;
//            progressText = "Good Luck friend! Your anxiety level has decreased by " +presentage+"% compared to your previous assessment.";
//        } else if (previousMarks == totalMarks) {
//            progressText = "Your level remains the same.";
//        } else if (previousMarks == 0 && totalMarks > previousMarks) {
//            progressText = "To see your progress, you need to retake the anxiety test in 3 days.";
//        } else {
//            progressText = "To learn about your progress, please complete the Anxiety Test.";
//        }

        // clear the shared preferences marks
//        SharedPreferences sharedPreferences1 = getSharedPreferences("User_Marks",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences1.edit();
//        editor.remove("Full_Marks");
//        editor.apply();

        // set profile values
        tv_email.setText(email);
        tv_progress.setText(anx_recommendation);
        tv_recommendation.setText(dep_recommendation);


        // call the methods
        goHome();
        setBtnLogout();

    }


    public void goHome(){
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setBtnLogout(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // clear the user session shared preferences
                SharedPreferences userSession = getSharedPreferences("UserSession", MODE_PRIVATE);
                SharedPreferences.Editor userSessionEditor = userSession.edit();
                userSessionEditor.clear();
                userSessionEditor.apply();

                // clear the user marks shared preferences
                SharedPreferences userMarks = getSharedPreferences("User_Marks", MODE_PRIVATE);
                SharedPreferences.Editor userMarksEditor = userMarks.edit();
                userMarksEditor.clear();
                userMarksEditor.apply();

                // clear the user Recommendations shared preferences
                SharedPreferences recommendations = getSharedPreferences("Recommendation", MODE_PRIVATE);
                SharedPreferences.Editor recommendationsEditor = recommendations.edit();
                recommendationsEditor.clear();
                recommendationsEditor.apply();

                // redirect to the login page
                Intent intent = new Intent(ProfileActivity.this, Login.class);
                startActivity(intent);
                Toasty.success(getApplicationContext(),"Logout Success",Toasty.LENGTH_SHORT).show();

            }
        });
    }
}