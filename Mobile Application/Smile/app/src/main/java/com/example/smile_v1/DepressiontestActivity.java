package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DepressiontestActivity extends AppCompatActivity {

    //references
    TextView txtName;

    Button btnTest;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_depressiontest);

        //Initialization
        txtName = findViewById(R.id.txtName);
        btnTest = findViewById(R.id.btnTest);

        //retrieve the name from the shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        name = sharedPreferences.getString("name", "N/A");

        txtName.setText(name + ",");

        //call to the methods
        gotoText();
    }

    public void gotoText(){
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepressiontestActivity.this, DepressionQuestiononeActivity.class);
                startActivity(intent);
            }
        });
    }
}