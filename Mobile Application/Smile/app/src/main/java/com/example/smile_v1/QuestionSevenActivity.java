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

public class QuestionSevenActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_question_seven);

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

        // create the shared preferences
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

        // save the marks to the shared preferences
        editor.putInt("Question_Seven", marks);
        editor.apply();

        // create a new variable to store total marks
        int newMarks;
        newMarks = previousMarks + marks;

        // test the marks
        Log.d("Marks6", String.valueOf(marks));
        Log.d("New_Marks6", String.valueOf(newMarks));

        // pass the new marks to the activity three
        Intent intent = new Intent(getApplicationContext(), EndQuestionActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("USER_MARKS",newMarks);
        intent.putExtras(extras);
        startActivity(intent);

        // store the newMarks to the database

        // request queue
        RequestQueue queue = Volley.newRequestQueue(QuestionSevenActivity.this);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferencesMarks = getSharedPreferences("UserSession", MODE_PRIVATE);
        int id = sharedPreferencesMarks.getInt("id",0);

        String url = "http://192.168.56.1:8080/api/anxiety/student/updateMarks/"+id;

        // String Request Object
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("Update Successfully")){
                    //Toast.makeText(QuestionSevenActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    Toasty.success(getApplicationContext(), "Successfully Completed", Toasty.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(QuestionSevenActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                Toasty.error(getApplicationContext(),"Failed To Update the Marks",Toasty.LENGTH_SHORT).show();

            }
        }){
            // pass the values to the database
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("marks", String.valueOf(newMarks));

                return params;
            }
        };

        queue.add(stringRequest);
    }
}