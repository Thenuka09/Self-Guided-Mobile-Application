package com.example.smile_v1;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class Login extends AppCompatActivity {

    Button btnSignup, btnGo, btnLoginCouncilor, btnForgetPassword;

    ImageView logoImage;
    TextView txtHello, txtSignIn;
    TextInputLayout email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Initialization
        btnSignup = findViewById(R.id.btnSignup);
        btnGo = findViewById(R.id.btnGo);
        logoImage = findViewById(R.id.logoImage);
        txtHello = findViewById(R.id.txtHello);
        txtSignIn = findViewById(R.id.txtSignIn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLoginCouncilor = findViewById(R.id.btnLoginCouncilor);
        btnForgetPassword = findViewById(R.id.btnForgetPassword);

        // signUp methods calling
        signUp();

        // logIn method calling
        logIn();

        // setBtnLoginCouncilor method calling
        setBtnLoginCouncilor();

        // call the goResetPassword method
        goResetPassword();
    }

    public void signUp(){
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignUp.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(logoImage, "logoImage");
                pairs[1] = new Pair<View, String>(txtHello, "logoText");
                pairs[2] = new Pair<View, String>(txtSignIn, "txtSignIn");
                pairs[3] = new Pair<View, String>(email, "email_test");
                pairs[4] = new Pair<View, String>(password, "password_test");
                pairs[5] = new Pair<View, String>(btnGo, "btnGO");
                pairs[6] = new Pair<View, String>(btnSignup, "signUp");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
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

    public void logIn(){

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validEmail() | !validPassword()){
                    return;
                }

                // Request queue
                RequestQueue queue = Volley.newRequestQueue(Login.this);

                // URL
                String url = "http://192.168.56.1:8080/api/anxiety/student/login";

                //set parameters
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("email", email.getEditText().getText().toString());
                params.put("password", password.getEditText().getText().toString());

                // set request object
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    // get values from request object
                                    String name = (String) response.get("name");
                                    String email = (String) response.get("email");
                                    String faculty = (String) response.get("faculty");
                                    String student_id = (String) response.get("student_id");
                                    String phone_number = (String) response.get("phone_number");
                                    int id = (int) response.get("id");
                                    int marks = (int) response.get("marks");

                                    // store the data using shared preferences
                                    SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("name", name);
                                    editor.putString("email", email);
                                    editor.putString("faculty", faculty);
                                    editor.putString("student_id", student_id);
                                    editor.putString("phone_number", phone_number);
                                    editor.putInt("id", id);
                                    editor.putInt("previousMarks",marks);
                                    editor.apply();

                                    // start activity
                                    Intent welcomeScreen = new Intent(getApplicationContext(), NavigationActivity.class);
                                    startActivity(welcomeScreen);
                                    Toasty.success(getApplicationContext(), "Login Success", Toasty.LENGTH_SHORT).show();
                                    //Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    finish();


                                }catch (JSONException e){
                                    e.printStackTrace();
                                    System.out.println(e.getMessage());

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        System.out.println(error.getMessage());

                        Toasty.error(getApplicationContext(), "Login Failed, Try again", Toasty.LENGTH_SHORT).show();
                        //Toast.makeText(Login.this, "Login Failed, Try again", Toast.LENGTH_SHORT).show();
                    }
                }); // end of set request object

                queue.add(jsonObjectRequest);
            }
        });

    }

    public void setBtnLoginCouncilor(){
        btnLoginCouncilor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CounsilorLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void goResetPassword(){
        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

}