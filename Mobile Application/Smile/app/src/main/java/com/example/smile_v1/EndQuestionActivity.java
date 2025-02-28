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

public class EndQuestionActivity extends AppCompatActivity {

    private int previousMarks;
    private String userSay;

    private int totalMarks;

    // initialization
    RadioButton rbNotDifficultAtAll, rbSomewhatDifficult, rbVeyDifficult, rbExtremelyDifficult;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_question);

        //catch the pass marks
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        previousMarks = extras.getInt("USER_MARKS");

        // assign the previousMarks to the totalMarks
        totalMarks = previousMarks;

        // referencing
        rbNotDifficultAtAll = findViewById(R.id.rbNotDifficultAtAll);
        rbSomewhatDifficult = findViewById(R.id.rbSomewhatDifficult);
        rbVeyDifficult = findViewById(R.id.rbVeyDifficult);
        rbExtremelyDifficult = findViewById(R.id.rbExtremelyDifficult);
        btnFinish = findViewById(R.id.btnFinish);

        // create a shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("User_Marks", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // store the marks to the shared preference
        editor.putInt("Full_Marks", totalMarks);
        editor.apply();

        //test the marks
        Log.d("full_marks", String.valueOf(totalMarks));

        // call to the function
        btnFinish.setOnClickListener(v -> buttonFinish());

        // enable and set the background of the button
//        RadioGroup radioGroup = findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            btnFinish.setEnabled(true);
//            btnFinish.setBackgroundResource(R.drawable.green_btn);
//        });

    }

    private void buttonFinish() {

        userSay = "";

        if (rbNotDifficultAtAll.isChecked()) {
            userSay = "Not Difficult at All";
        } else if (rbSomewhatDifficult.isChecked()) {
            userSay = "Somewhat Difficult";
        } else if (rbVeyDifficult.isChecked()) {
            userSay = "Very Difficult";
        } else if (rbExtremelyDifficult.isChecked()) {
            userSay = "Extremely Difficult";
        }

        // store the userSay to the new variable
        String whatIsUserSay;
        whatIsUserSay = userSay;

        // test the userSay
        Log.d("User_say", whatIsUserSay);

        // store the userSay to the database

        // request queue
        RequestQueue queue = Volley.newRequestQueue(EndQuestionActivity.this);

        // retrieve the id
        SharedPreferences sharedPreferencesMarks = getSharedPreferences("UserSession", MODE_PRIVATE);
        int id = sharedPreferencesMarks.getInt("id", 0);

        String url = "http://192.168.56.1:8080/api/anxiety/student/updateUserSay/" + id;

        // String Request Object
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("Update Successfully")) {
                    //Toast.makeText(EndQuestionActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    Toasty.success(getApplicationContext(), "Congradulation My Friend, You done!", Toasty.LENGTH_SHORT).show();

                    // create the intent goto the results activity
                    //gotoResult();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(EndQuestionActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                Toasty.error(getApplicationContext(), "Failed To Updated", Toasty.LENGTH_SHORT).show();
            }
        }) {
            // pass the values to the database
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_say", String.valueOf(whatIsUserSay));

                return params;
            }
        };

        queue.add(stringRequest);

        //retrieve the total marks
        SharedPreferences sharedPreferencesFullMarks = getSharedPreferences("User_Marks", MODE_PRIVATE);
        int totalMarks = sharedPreferencesFullMarks.getInt("Full_Marks", 0);

        int imageId = 0;

        if (totalMarks <= 4 || totalMarks == 0) {
            imageId = R.drawable.minimal_anxiety_rb;
        } else if (totalMarks <= 9 || totalMarks == 5) {
            imageId = R.drawable.mild_anxiety_rb;
        } else if (totalMarks <= 14 || totalMarks == 10) {
            imageId = R.drawable.moderate_anxiety_rb;
        } else if (totalMarks <= 21 || totalMarks == 15) {
            imageId = R.drawable.severe_anxiety_rb;
        }

        Intent intent = new Intent(EndQuestionActivity.this, ResultsActivity.class);
        intent.putExtra("Image", imageId);
        startActivity(intent);

    }

//    public void gotoResult(){
//
//        // retrieve the total marks
//        SharedPreferences sharedPreferencesMarks = getSharedPreferences("User_Marks", MODE_PRIVATE);
//        int totalMarks = sharedPreferencesMarks.getInt("Full_Marks",0);
//
//        int imageId = 0;
//
//        if(totalMarks <= 4 || totalMarks == 0){
//            imageId = R.drawable.minimal_anxiety_rb;
//        } else if (totalMarks <=9 || totalMarks == 5) {
//            imageId = R.drawable.mild_anxiety_rb;
//        } else if (totalMarks <= 14 || totalMarks == 10) {
//            imageId = R.drawable.moderate_anxiety_rb;
//        } else if (totalMarks <=21 || totalMarks == 15) {
//            imageId = R.drawable.severe_anxiety_rb;
//        }
//
//        Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
//        intent.putExtra("Image",imageId);
//        startActivity(intent);
//
//    }
}