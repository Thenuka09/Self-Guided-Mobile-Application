package com.example.smile_v1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        mediaPlayer = MediaPlayer.create(this, R.raw.home_sound);
        btnPlay = findViewById(R.id.btnPlay);

        // Initialize the MediaPlayer
        initializeMediaPlayer();

        mediaPlayer.setLooping(true);

        mediaPlayer.start();

        // call to the play function
        play();
    }

    private void initializeMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.home_sound);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void play() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Stop the music when navigating to the next activity
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                Intent intent = new Intent(SplashScreen.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop the MediaPlayer when the activity is paused
        stopMediaPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reinitialize the MediaPlayer when the activity resumes
        if (mediaPlayer == null) {
            initializeMediaPlayer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer if the activity is destroyed
        stopMediaPlayer();
    }

    private void stopMediaPlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
