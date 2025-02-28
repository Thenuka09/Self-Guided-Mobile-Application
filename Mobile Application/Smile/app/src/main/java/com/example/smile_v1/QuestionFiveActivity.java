package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionFiveActivity extends AppCompatActivity {

    private int previousMarks;
    private int marks;

    // initialization
    RadioButton rbNotAtAll, rbSeveralDays, rbMoreThanHlafDays, rbNearlyEveryDay;

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_question_five);

        //catch the pass marks
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        previousMarks = extras.getInt("USER_MARKS");

        // referencing
        rbNotAtAll = findViewById(R.id.rbNotAtAll);
        rbSeveralDays = findViewById(R.id.rbSeveralDays);
        rbMoreThanHlafDays = findViewById(R.id.rbMoreThanHlafDays);
        rbNearlyEveryDay = findViewById(R.id.rbNearlyEveryDay);
        btnNext = findViewById(R.id.btnNext);

        // call the method
        btnNext.setOnClickListener(v -> buttonNext());

        // enable and set the background of the button
//        RadioGroup radioGroup = findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            btnNext.setEnabled(true);
//            btnNext.setBackgroundResource(R.drawable.green_btn);
//        });

    }

    private void buttonNext() {

        marks = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("User_Marks", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (rbNotAtAll.isChecked()){
            marks = 0;
        } else if (rbSeveralDays.isChecked()) {
            marks = 1;
        } else if (rbMoreThanHlafDays.isChecked()) {
            marks = 2;
        } else if (rbNearlyEveryDay.isChecked()) {
            marks = 3;
        }

        editor.putInt("Question_Five",marks);
        editor.apply();

        // create new variable to store total mark
        int newMarks;
        newMarks = previousMarks + marks;

        // test the values
        Log.d("Marks4", String.valueOf(marks));
        Log.d("New_Marks4", String.valueOf(newMarks));

        // pass the new marks to the activity three
        Intent intent = new Intent(getApplicationContext(), QuestionSixActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("USER_MARKS",newMarks);
        intent.putExtras(extras);
        startActivity(intent);
    }
}