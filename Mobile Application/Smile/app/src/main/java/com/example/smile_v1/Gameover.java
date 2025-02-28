package com.example.smile_v1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Gameover extends AppCompatActivity {

    MediaPlayer lossSound,seaSound;

    Button playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gameover);

        lossSound = MediaPlayer.create(this, R.raw.game_loss);
        seaSound = MediaPlayer.create(this,R.raw.sea);
        playBtn = findViewById(R.id.playBtn);

        // Initialize media players
        initializeMediaPlayers();

        lossSound.setLooping(false);
        seaSound.setLooping(true);

        lossSound.start();

        //after playing the loss sound the sea sound become play
        lossSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                seaSound.start();
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seaSound.stop();

                Intent intent = new Intent(Gameover.this, ActivitiesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeMediaPlayers() {
        lossSound = MediaPlayer.create(this, R.raw.game_win);
        seaSound = MediaPlayer.create(this, R.raw.sea);

        lossSound.setLooping(false);
        seaSound.setLooping(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause both media players when the activity goes to the background
        pauseMediaPlayer(lossSound);
        pauseMediaPlayer(seaSound);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Resume the sea sound if it was playing before the activity was paused
        if (seaSound != null && !seaSound.isPlaying()) {
            seaSound.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release media players when the activity is destroyed
        stopMediaPlayer(lossSound);
        stopMediaPlayer(seaSound);
    }

    private void stopMediaPlayer(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void pauseMediaPlayer(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }
}