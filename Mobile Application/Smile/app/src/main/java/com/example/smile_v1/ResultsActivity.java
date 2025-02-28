package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    // initialization
    ImageView IM_Level;

    TextView txtLevel;
    Button btnRecommendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_results);

        // referencing
        IM_Level = findViewById(R.id.IM_Level);
        btnRecommendation = findViewById(R.id.btnRecommendation);
        txtLevel = findViewById(R.id.txtLevel);

        //catch the image
        int imageResId = getIntent().getIntExtra("Image", R.drawable.default_image);
        IM_Level.setImageResource(imageResId);

        // retrieve the marks from the shared preferences
        SharedPreferences sharedPreferencesMarks = getSharedPreferences("User_Marks", MODE_PRIVATE);
        int fullMarks = sharedPreferencesMarks.getInt("Full_Marks", 0);

        if (fullMarks <= 4 || fullMarks == 0) {
            txtLevel.setText("You’re chillin’ like a villain. Keep up the good vibes...!");
        } else if (fullMarks <= 9 || fullMarks == 5) {
            txtLevel.setText("A little stress, huh? Maybe a deep breath or a snack will help...!");
        } else if (fullMarks <= 14 || fullMarks == 10) {
            txtLevel.setText("Feeling a bit tangled? Untangle with a nap, a walk, or some memes...!");
        } else if (fullMarks <= 21 || fullMarks == 15) {
            txtLevel.setText("Uh-oh, brain overload! Time to pet a dog, scream into a pillow, or dance it out...!");
        }

        buttonRecommendation();
        //btnRecommendation.setOnClickListener(v -> buttonRecommendation());
    }

    private void buttonRecommendation() {
        btnRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, PredictionActivity.class);
                startActivity(intent);
            }
        });

    }
}