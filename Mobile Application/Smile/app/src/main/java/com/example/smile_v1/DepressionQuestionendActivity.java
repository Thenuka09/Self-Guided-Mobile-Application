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

public class DepressionQuestionendActivity extends AppCompatActivity {

    //Referencing
    Button btnNext;
    RadioButton rbNotAtAll, rbSeveralDays, rbMoreThanHlafDays, rbNearlyEveryDay;

    private String dep_userSay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_depression_questionend);

        //referencing
        btnNext = findViewById(R.id.btnNext);
        rbNotAtAll = findViewById(R.id.rbNotAtAll);
        rbSeveralDays = findViewById(R.id.rbSeveralDays);
        rbMoreThanHlafDays = findViewById(R.id.rbMoreThanHlafDays);
        rbNearlyEveryDay = findViewById(R.id.rbNearlyEveryDay);

        btnNext.setOnClickListener(v -> storeUserSay());

        // enable and set the background of the button
//        RadioGroup radioGroup = findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            btnNext.setEnabled(true);
//            btnNext.setBackgroundResource(R.drawable.green_btn);
//        });
    }

    public void storeUserSay() {

        dep_userSay = "";

        //create shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("Depression_Marks", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (rbNotAtAll.isChecked()) {
            dep_userSay = "Not difficult at all";
        } else if (rbSeveralDays.isChecked()) {
            dep_userSay = "Somewhat difficult";
        } else if (rbMoreThanHlafDays.isChecked()) {
            dep_userSay = "Very difficult";
        } else if (rbNearlyEveryDay.isChecked()) {
            dep_userSay = "Extremely difficult";
        }

        //save the marks to shared preferences
        editor.putString("Depression_Question_End", dep_userSay);
        editor.apply();

        //test the marks
        Log.d("Depression_End", String.valueOf(dep_userSay));

//        //goto result question
//        Intent intent = new Intent(DepressionQuestionendActivity.this, DepressionQuestionresultActivity.class);
//        startActivity(intent);

        // store the userSay to the database

        // request queue
        RequestQueue queue = Volley.newRequestQueue(DepressionQuestionendActivity.this);

        // retrieve the id
        SharedPreferences sharedPreferencesMarks = getSharedPreferences("UserSession", MODE_PRIVATE);
        int id = sharedPreferencesMarks.getInt("id", 0);

        String url = "http://192.168.56.1:8080/api/anxiety/student/updateDepressionUserSay/" + id;

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
                params.put("dep_userSay", String.valueOf(dep_userSay));

                return params;
            }
        };

        queue.add(stringRequest);

        //goto result question
        Intent intent = new Intent(DepressionQuestionendActivity.this, DepressionQuestionresultActivity.class);
        startActivity(intent);
    }
}