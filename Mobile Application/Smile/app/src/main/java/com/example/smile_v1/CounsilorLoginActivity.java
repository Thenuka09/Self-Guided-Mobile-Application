package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class CounsilorLoginActivity extends AppCompatActivity {

    // Initialization
    TextInputLayout email, password;
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_counsilor_login);

        // Referencing
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnGo = findViewById(R.id.btnGo);

        // call the login method
        setLogin();

    }

    private boolean validEmail() {
        String studentEmail = email.getEditText().getText().toString();
        String emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$";

        if (studentEmail.isEmpty()) {
            email.setError("Email cannot be blank");
            return false;
        } else if (!studentEmail.matches(emailPattern)) {
            email.setError("This is not a valid email");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validPassword(){
        String studentPassword = password.getEditText().getText().toString();

        if (studentPassword.isEmpty()){
            password.setError("password cannot be blank");
            return false;
        }else {
            password.setError(null);
            return true;
        }
    }

    public void setLogin(){
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validEmail() | !validPassword()){
                    return;
                }

                // Request queue
                RequestQueue queue = Volley.newRequestQueue(CounsilorLoginActivity.this);

                // URL
                String url = "http://192.168.56.1:8080/api/anxiety/counselor/login";

                //set parameters
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("email", email.getEditText().getText().toString());
                params.put("password", password.getEditText().getText().toString());

                // set request object
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            // Check if the login was successful or not
                            if (response.has("status") && response.getString("status").equals("fail")) {

                                Toasty.error(CounsilorLoginActivity.this, "Incorrect username or password", Toasty.LENGTH_SHORT).show();
                            }else {

                                // get values from request object
                                String name = (String) response.get("name");
                                String email = (String) response.get("email");
                                String phone_number = (String) response.get("phone_number");
                                int id = (int) response.get("id");

                                // store the data using shared preferences
                                SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name", name);
                                editor.putString("email", email);
                                editor.putString("phone_number", phone_number);
                                editor.putInt("id", id);
                                editor.apply();

                                // start activity
                                Intent intent = new Intent(CounsilorLoginActivity.this, CounsilorWelcomeActivity.class);
                                startActivity(intent);
                                Toasty.success(CounsilorLoginActivity.this, "Login Success", Toasty.LENGTH_SHORT).show();
                                finish();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }

                        // end of the try catch block
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        System.out.println(error.getMessage());

                        Toasty.error(CounsilorLoginActivity.this, "Login Failed, Try Again",Toasty.LENGTH_SHORT).show();
                    }
                }); // end the set request object

                queue.add(jsonObjectRequest);

            }
        });

    }
}