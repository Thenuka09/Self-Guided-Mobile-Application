package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class WelcomeActivity extends AppCompatActivity {

    Button btnGetStarted;
    TextView txtName;

    private String consent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        // initialization
        btnGetStarted = findViewById(R.id.btnGetStarted);
        txtName = findViewById(R.id.txtName);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "N/A");

        // set profile values
        txtName.setText(name + " ,");

        //call the method
        getStarted();
    }


    public void getStarted() {
        btnGetStarted.setOnClickListener(v -> {

                    new FancyGifDialog.Builder(WelcomeActivity.this)
                            .setTitle("Consent for Data Usage") // You can also send title like R.string.from_resources
                            .setMessage("Do you agree to store and view your marks and personal data for further support? Please note that your data will only be accessed by the university counselor.") // or pass like R.string.description_from_resources
                            .setTitleTextColor(R.color.dark_blue)
                            .setDescriptionTextColor(R.color.black)
                            .setNegativeBtnText("No") // or pass it like android.R.string.cancel
                            .setPositiveBtnBackground(R.color.green)
                            .setPositiveBtnText("Yes") // or pass it like android.R.string.ok
                            .setNegativeBtnBackground(R.color.red)
                            .setGifResource(R.drawable.success)   //Pass your Gif here
                            .isCancellable(true)
                            .OnPositiveClicked(new FancyGifDialogListener() {
                                @Override
                                public void OnClick() {
                                    consent = "Yes";
                                    updateConsent(consent);
                                    Toasty.success(WelcomeActivity.this, "Updated", Toasty.LENGTH_SHORT).show();
                                    Intent intent = new Intent(WelcomeActivity.this, DashboardActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .OnNegativeClicked(new FancyGifDialogListener() {
                                @Override
                                public void OnClick() {
                                    consent = "No";
                                    updateConsent(consent);
                                    Toasty.success(WelcomeActivity.this, "Updated", Toasty.LENGTH_SHORT).show();
                                    Intent intent = new Intent(WelcomeActivity.this, DashboardActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .build();
                }
        );
    }

    public void updateConsent(String consent) {

        // create the API

        // store the newMarks to the database

        // request queue
        RequestQueue queue = Volley.newRequestQueue(WelcomeActivity.this);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferencesMarks = getSharedPreferences("UserSession", MODE_PRIVATE);
        int id = sharedPreferencesMarks.getInt("id", 0);

        String url = "http://192.168.56.1:8080/api/anxiety/student/updateConsent/" + id;

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
                Toasty.error(getApplicationContext(), "Failed To Update the Consent", Toasty.LENGTH_SHORT).show();

            }
        }) {
            // pass the values to the database
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("consent", String.valueOf(consent));
                return params;
            }
        };

        queue.add(stringRequest);
    }
}