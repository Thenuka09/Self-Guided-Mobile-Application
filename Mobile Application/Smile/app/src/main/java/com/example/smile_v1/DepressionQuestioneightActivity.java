package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class DepressionQuestioneightActivity extends AppCompatActivity {

    //Referencing
    Button btnNext;
    RadioButton rbNotAtAll, rbSeveralDays, rbMoreThanHlafDays, rbNearlyEveryDay;

    public int depressionMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_depression_questioneight);

        //referencing
        btnNext =  findViewById(R.id.btnNext);
        rbNotAtAll =  findViewById(R.id.rbNotAtAll);
        rbSeveralDays =  findViewById(R.id.rbSeveralDays);
        rbMoreThanHlafDays =  findViewById(R.id.rbMoreThanHlafDays);
        rbNearlyEveryDay =  findViewById(R.id.rbNearlyEveryDay);

        btnNext.setOnClickListener(v -> storeMarks());

        // enable and set the background of the button
//        RadioGroup radioGroup = findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            btnNext.setEnabled(true);
//            btnNext.setBackgroundResource(R.drawable.green_btn);
//        });
    }

    public void storeMarks(){

        depressionMark = 0;

        //create shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("Depression_Marks", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(rbNotAtAll.isChecked()){
            depressionMark = 0;
        } else if (rbSeveralDays.isChecked()) {
            depressionMark = 1;
        } else if (rbMoreThanHlafDays.isChecked()) {
            depressionMark = 2;
        }else if (rbNearlyEveryDay.isChecked()){
            depressionMark = 3;
        }

        //save the marks to shared preferences
        editor.putInt("Depression_Question_Eight", depressionMark);
        editor.apply();

        //test the marks
        Log.d("Depression_Eight", String.valueOf(depressionMark));

        //goto second question
        Intent intent = new Intent(DepressionQuestioneightActivity.this, DepressionQuestionnineActivity.class);
        startActivity(intent);
    }
}