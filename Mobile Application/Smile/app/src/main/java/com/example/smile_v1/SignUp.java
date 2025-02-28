package com.example.smile_v1;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class SignUp extends AppCompatActivity {

    //reference
    ImageView logoImage;
    TextView txtWelcome, txtSignUp;
    TextInputLayout email, password, name, faculty, studentID, phoneNumber, reEnterPassword;
    Button btnLogin, btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Initialization
        logoImage = findViewById(R.id.logoImage);
        txtWelcome = findViewById(R.id.txtWelcome);
        txtSignUp = findViewById(R.id.txtSignUp);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        name = findViewById(R.id.fullName);
        faculty= findViewById(R.id.Faculty);
        studentID = findViewById(R.id.studentID);
        phoneNumber = findViewById(R.id.pNumber);
        reEnterPassword = findViewById(R.id.reenterPassword);

        // calling the login method
        logIn();

        // call the signUP method
        signUP();

    }

    public void logIn(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Login.class);


                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(logoImage, "logoImage");
                pairs[1] = new Pair<View, String>(txtWelcome, "logoText");
                pairs[2] = new Pair<View, String>(txtSignUp, "txtSignIn");
                pairs[3] = new Pair<View, String>(email, "email_test");
                pairs[4] = new Pair<View, String>(password, "password_test");
                pairs[5] = new Pair<View, String>(btnLogin, "btnGO");
                pairs[6] = new Pair<View, String>(btnSignUp, "signUp");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }

    private boolean validateName(){
        String regName = name.getEditText().getText().toString();

        if(regName.isEmpty()){
            name.setError("Name can not be Blank");
            return false;
        }else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFaculty(){
        String facultyName = faculty.getEditText().getText().toString();

        if(facultyName.isEmpty()){
            faculty.setError("Faculty can not be Blank");
            return false;
        }else {
            faculty.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validStudentID(){
        String sId = studentID.getEditText().getText().toString();

        if (sId.isEmpty()){
            studentID.setError("Student ID can not be Blank");
            return false;
        } else {
            studentID.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validPhoneNumber(){
        String number = phoneNumber.getEditText().getText().toString();

        if(number.isEmpty()){
            phoneNumber.setError("Phone number can not be blank");
            return false;
        } else if (number.length() < 10) {
            phoneNumber.setError("Enter correct Number");
            return false;
        }else {
            phoneNumber.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
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
        String studentReEnterPassword = reEnterPassword.getEditText().getText().toString();

        if (studentReEnterPassword.isEmpty()){
            reEnterPassword.setError("re-enter password cannot be blank");
            return false;
        } else if (!studentReEnterPassword.matches(studentPassword)) {
            reEnterPassword.setError("password does not matches");
            return false;
        }else {
            reEnterPassword.setError(null);
            return true;
        }
    }

    public void signUP(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateName() | !validateFaculty() | !validStudentID() | !validPhoneNumber()
                | !validEmail() | !validPassword() | !validReenterPassword()){
                    return;
                }

                // request queue
                RequestQueue queue = Volley.newRequestQueue(SignUp.this);

                // URL
                String url = "http://192.168.56.1:8080/api/anxiety/student/register";

                // String Request Object
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Register Success")) {

                            name.getEditText().setText("");
                            faculty.getEditText().setText("");
                            studentID.getEditText().setText("");
                            phoneNumber.getEditText().setText("");
                            email.getEditText().setText("");
                            password.getEditText().setText("");
                            reEnterPassword.getEditText().setText("");

                            // toast message if user-registration was successfully
                            //Toast.makeText(SignUp.this, "Registration Success", Toast.LENGTH_LONG).show();
                            Toasty.success(getApplicationContext(), "Registration Success", Toasty.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // registration failed
                        //Toast.makeText(SignUp.this, "Registration Failed, Try again", Toast.LENGTH_SHORT).show();
                        Toasty.error(getApplicationContext(), "Registration Failed, Try again", Toasty.LENGTH_SHORT).show();

                    }
                }){
                    // pass the values to the spring boot endpoint
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email.getEditText().getText().toString());
                        params.put("faculty", faculty.getEditText().getText().toString());
                        params.put("name", name.getEditText().getText().toString());
                        params.put("password", password.getEditText().getText().toString());
                        params.put("phone_number", phoneNumber.getEditText().getText().toString());
                        params.put("student_id", studentID.getEditText().getText().toString());

                        return params;
                    }
                }; // end of the String Request Object

                queue.add(stringRequest);

            }
        });
    }
}