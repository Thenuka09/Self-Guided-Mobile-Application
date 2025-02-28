package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class DepressionQuestionnineActivity extends AppCompatActivity {

    //Referencing
    Button btnNext;
    RadioButton rbNotAtAll, rbSeveralDays, rbMoreThanHlafDays, rbNearlyEveryDay;

    private int depressionMark, totalMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_depression_questionnine);

        //referencing
        btnNext = findViewById(R.id.btnNext);
        rbNotAtAll = findViewById(R.id.rbNotAtAll);
        rbSeveralDays = findViewById(R.id.rbSeveralDays);
        rbMoreThanHlafDays = findViewById(R.id.rbMoreThanHlafDays);
        rbNearlyEveryDay = findViewById(R.id.rbNearlyEveryDay);

        btnNext.setOnClickListener(v -> storeMarks());

        // enable and set the background of the button
//        RadioGroup radioGroup = findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            btnNext.setEnabled(true);
//            btnNext.setBackgroundResource(R.drawable.green_btn);
//        });
    }

    public void storeMarks() {

        depressionMark = 0;

        //create shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("Depression_Marks", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (rbNotAtAll.isChecked()) {
            depressionMark = 0;
        } else if (rbSeveralDays.isChecked()) {
            depressionMark = 1;
        } else if (rbMoreThanHlafDays.isChecked()) {
            depressionMark = 2;
        } else if (rbNearlyEveryDay.isChecked()) {
            depressionMark = 3;
        }

        //save the marks to shared preferences
        editor.putInt("Depression_Question_Nine", depressionMark);
        editor.apply();

        //test the marks
        Log.d("Depression_Nine", String.valueOf(depressionMark));

        // Retrieve marks from SharedPreferences
        //SharedPreferences sharedPreferencesMarks = getSharedPreferences("Depression_Marks", MODE_PRIVATE);
        int questionOneMarks = sharedPreferences.getInt("Depression_Question_One", 0);
        int questionTwoMarks = sharedPreferences.getInt("Depression_Question_Two", 0);
        int questionThreeMarks = sharedPreferences.getInt("Depression_Question_Three", 0);
        int questionFourMarks = sharedPreferences.getInt("Depression_Question_Four", 0);
        int questionFiveMarks = sharedPreferences.getInt("Depression_Question_Five", 0);
        int questionSixMarks = sharedPreferences.getInt("Depression_Question_Six", 0);
        int questionSevenMarks = sharedPreferences.getInt("Depression_Question_Seven", 0);
        int questionEightMarks = sharedPreferences.getInt("Depression_Question_Eight", 0);
        int questionNineMarks = sharedPreferences.getInt("Depression_Question_Nine", 0);

        totalMarks = (questionOneMarks + questionTwoMarks + questionThreeMarks + questionFourMarks + questionFiveMarks + questionSixMarks + questionSevenMarks + questionEightMarks + questionNineMarks);

        //save the total marks in the shared preferences
        editor.putInt("Depression_TotalMarks", totalMarks);
        editor.apply();

        Log.d("Total_Marks", String.valueOf(totalMarks));

//        //goto end question
//        Intent intent = new Intent(DepressionQuestionnineActivity.this, DepressionQuestionendActivity.class);
//        startActivity(intent);

        // store the newMarks to the database

        // request queue
        RequestQueue queue = Volley.newRequestQueue(DepressionQuestionnineActivity.this);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferencesMarks = getSharedPreferences("UserSession", MODE_PRIVATE);
        int id = sharedPreferencesMarks.getInt("id", 0);

        String url = "http://192.168.56.1:8080/api/anxiety/student/updateDepressionMarks/" + id;

        // String Request Object
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("Update Successfully")) {
                    //Toast.makeText(QuestionSevenActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    Toasty.success(getApplicationContext(), "Successfully Completed", Toasty.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(QuestionSevenActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                Toasty.error(getApplicationContext(), "Failed To Update the Marks", Toasty.LENGTH_SHORT).show();

            }
        }) {
            // pass the values to the database
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dep_marks", String.valueOf(totalMarks));

                return params;
            }
        };

        queue.add(stringRequest);

        //goto end question
        Intent intent = new Intent(DepressionQuestionnineActivity.this, DepressionQuestionendActivity.class);
        startActivity(intent);
    }
}