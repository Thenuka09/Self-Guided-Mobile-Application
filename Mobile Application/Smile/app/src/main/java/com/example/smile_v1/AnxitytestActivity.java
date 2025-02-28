package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnxitytestActivity extends AppCompatActivity {

    // TestView Initialization
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_anxitytest);

        // Test View Referencing
        txtName = findViewById(R.id.txtName);

        // Retrieve data from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "N/A");

        // set the name
        txtName.setText(name+ " ,");
    }

    public void starttheTest(View view) {

        Intent intent = new Intent(getApplicationContext(), QuestionOneActivity.class);
        startActivity(intent);

    }
}