package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionOneActivity extends AppCompatActivity {

    // initialization

    RadioButton rbNotAtAll, rbSeveralDays, rbMoreThanHlafDays, rbNearlyEveryDay;

    Button btnNext;

    private int marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_question_one);

        // referencing
        rbNotAtAll = findViewById(R.id.rbNotAtAll);
        rbSeveralDays = findViewById(R.id.rbSeveralDays);
        rbMoreThanHlafDays = findViewById(R.id.rbMoreThanHlafDays);
        rbNearlyEveryDay = findViewById(R.id.rbNearlyEveryDay);
        btnNext = findViewById(R.id.btnNext);

        // calling the method
        // Attach click listener to the button
        btnNext.setOnClickListener(v -> buttonNext());

        // enable and set the background of the button
//        RadioGroup radioGroup = findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            btnNext.setEnabled(true);
//            btnNext.setBackgroundResource(R.drawable.green_btn);
//        });
    }

    public void buttonNext(){

        marks = 0;

        // store the marks into the Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("User_Marks", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if(rbNotAtAll.isChecked()){
            marks = 0;
        } else if (rbSeveralDays.isChecked()) {
            marks = 1;
        } else if (rbMoreThanHlafDays.isChecked()) {
            marks = 2;
        }else if(rbNearlyEveryDay.isChecked()) {
            marks = 3;
        }

        // Save and Apply changes to SharedPreferences (key --> "Question_one")
        editor.putInt("Question_one", marks);
        editor.apply();

        int newMarks = marks + 0 ;

        // test the shared preference value
        Log.d("Marks",String.valueOf(marks));
        Log.d("New_Marks", String.valueOf(newMarks));

        //pass the marks to the QuestionFour
        Intent intent = new Intent(getApplicationContext(), QuestionTwoActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("USER_MARKS",newMarks);
        intent.putExtras(extras);
        startActivity(intent);

    }
}