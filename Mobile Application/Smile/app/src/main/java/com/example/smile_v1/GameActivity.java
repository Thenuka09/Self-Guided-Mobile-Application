package com.example.smile_v1;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private FlyingFishView gameView;
    private Handler handler = new Handler();
    private final static long interval = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create a container layout
        FrameLayout container = new FrameLayout(this);

        // Create the FlyingFishView
        gameView = new FlyingFishView(this);

        // Inflate additional layout (e.g., activity_main.xml)
        getLayoutInflater().inflate(R.layout.activity_game, container, true);

        // Add the FlyingFishView to the container
        container.addView(gameView);

        // Set the container as the content view
        setContentView(container);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, interval );
    }
}
