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

public class DepressionQuestionresultActivity extends AppCompatActivity {

    ImageView imgLevel;
    TextView txtLevel;
    Button btnRecommendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_depression_questionresult);

        imgLevel = findViewById(R.id.imgLevel);
        txtLevel = findViewById(R.id.txtLevel);
        btnRecommendation = findViewById(R.id.btnRecommendation);

        // retrieve the fullMarks
        SharedPreferences sharedPreferencesMarks = getSharedPreferences("Depression_Marks", MODE_PRIVATE);
        int totalMarks = sharedPreferencesMarks.getInt("Depression_TotalMarks", 0);

        if (totalMarks <= 4 || totalMarks == 0) {
            imgLevel.setImageResource(R.drawable.dep_result_minimal);
            txtLevel.setText("You’re doing well emotionally. Keep up the positive practices...!");
        } else if (totalMarks <= 9 || totalMarks == 5) {
            imgLevel.setImageResource(R.drawable.dep_result_mild);
            txtLevel.setText("You may be feeling a little off lately. Let's focus on small steps toward improvement...!");
        } else if (totalMarks <= 14 || totalMarks == 10) {
            imgLevel.setImageResource(R.drawable.dep_result_moderate);
            txtLevel.setText("It seems you're experiencing some challenges. Taking care of yourself is important...!");
        } else if (totalMarks <= 19 || totalMarks == 15) {
            imgLevel.setImageResource(R.drawable.dep_result_moderate_severe);
            txtLevel.setText("My friend, maybe it’s time to phone a friend or pet a dog...!");
        } else if (totalMarks <= 27 || totalMarks == 20) {
            imgLevel.setImageResource(R.drawable.dep_result_severe);
            txtLevel.setText("My friend, you're experiencing intense emotional distress; seeking professional help is importan...!");
        }

        //call the method
        gotoRecommendations();
    }

    public void gotoRecommendations(){
        btnRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepressionQuestionresultActivity.this, DepressionQuestionrecommendationsActivity.class);
                startActivity(intent);
            }
        });
    }
}