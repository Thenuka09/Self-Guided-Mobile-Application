package com.example.smile_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ResetPasswordActivity extends AppCompatActivity {

    // initialization
    TextInputLayout email, password, reenterPassword;
    Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reset_password);

        // References
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        reenterPassword = findViewById(R.id.reenterPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        // call the setBtnResetPassword method
        setBtnResetPassword();
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

    public boolean validPassword() {
        String studentPassword = password.getEditText().getText().toString();

        // Regular expression for a valid password
        String passwordVal = "^(?=.*[@#$%^&+=])(?=.*[a-zA-Z])(?=\\S+$).{6,}$";

        if (studentPassword.isEmpty()) {
            password.setError("Password cannot be blank");
            return false;
        } else if (!studentPassword.matches(".*[@#$%^&+=].*")) {
            password.setError("Password must contain at least one special character (@#$%^&+=)");
            return false;
        } else if (!studentPassword.matches(".*[a-zA-Z].*")) {
            password.setError("Password must contain at least one letter");
            return false;
        } else if (studentPassword.contains(" ")) {
            password.setError("Password cannot contain spaces");
            return false;
        } else if (studentPassword.length() < 6) {
            password.setError("Password must be at least six characters long");
            return false;
        } else if (!studentPassword.matches(passwordVal)) {
            password.setError("Weak password");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validReenterPassword() {
        String studentPassword = password.getEditText().getText().toString();
        String studentReEnterPassword = reenterPassword.getEditText().getText().toString();

        if (studentReEnterPassword.isEmpty()){
            reenterPassword.setError("re-enter password cannot be blank");
            return false;
        } else if (!studentReEnterPassword.matches(studentPassword)) {
            reenterPassword.setError("password does not matches");
            return false;
        }else {
            reenterPassword.setError(null);
            return true;
        }
    }

    public void setBtnResetPassword(){
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( !validEmail() | !validPassword() | !validReenterPassword()){
                    return;
                }


                // request queue
                RequestQueue queue = Volley.newRequestQueue(ResetPasswordActivity.this);

                // URL
                String url = "http://192.168.56.1:8080/api/anxiety/student/reset/password";

                // String Request Object
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Password reset successful")) {


                            email.getEditText().setText("");
                            password.getEditText().setText("");
                            reenterPassword.getEditText().setText("");

                            // toast message if user-registration was successfully
                            //Toast.makeText(SignUp.this, "Registration Success", Toast.LENGTH_LONG).show();
                            Toasty.success(getApplicationContext(), "Password reset successful", Toasty.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // registration failed
                        //Toast.makeText(SignUp.this, "Registration Failed, Try again", Toast.LENGTH_SHORT).show();
                        Toasty.error(getApplicationContext(), "Password reset Failed, Try again", Toasty.LENGTH_SHORT).show();

                    }
                }){
                    // pass the values to the spring boot endpoint
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email.getEditText().getText().toString());
                        params.put("password", password.getEditText().getText().toString());

                        return params;
                    }
                }; // end of the String Request Object

                queue.add(stringRequest);

            }
        });

    }


}