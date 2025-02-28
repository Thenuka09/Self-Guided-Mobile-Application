package com.example.smile_v1;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // after five seconds the screen move to another activity
    private static int splashScreen = 5000;

    //Animation Variables
    Animation topAnimation, bottomAnimation;
    ImageView imgSmile;
    TextView txtAppName, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Initializations
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        imgSmile = findViewById(R.id.imgSmile);
        txtAppName = findViewById(R.id.txtAppName);
        txtDescription = findViewById(R.id.txtDescription);

        //set the Animations to image view and two text views
        imgSmile.setAnimation(topAnimation);
        txtAppName.setAnimation(bottomAnimation);
        txtDescription.setAnimation(bottomAnimation);

        RedirectToLogging();

    }

    public void RedirectToLogging(){
        //delay method
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, Login.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(imgSmile, "logoImage");
                pairs[1] = new Pair<View, String>(txtAppName, "logoText");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent, options.toBundle());

            }
        },splashScreen);

    }
}