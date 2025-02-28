package com.example.smile_v1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;


public class FlyingFishView extends View {

    private Bitmap fish[] = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;
    private int canvasWidth, canvasHeight;

    private Bitmap yellowBall;

    private Bitmap greenBall;

    private Bitmap redBall;

    private int yellowX, yellowY, yellowSpeed = 16;

    private int greenX, greenY, greenSpeed = 20;

    private int redX, redY, redSpeed = 25;

    private int score, lifeCounterOfFish;
    private boolean touch = false;

    private Paint scorePaint = new Paint();

    private Bitmap life[] = new Bitmap[2];

    MediaPlayer touchSound;

    public FlyingFishView(Context context) {
        super(context);

        // initialize the media player
        touchSound = MediaPlayer.create(context, R.raw.click);

        // Load the original bitmap
        Bitmap originalFish = BitmapFactory.decodeResource(getResources(), R.drawable.fish2_rb);
        Bitmap originalFish2 = BitmapFactory.decodeResource(getResources(), R.drawable.fish_rb);

        // Resize the bitmap (set desired width and height)

        int newWidth = 350; // desired width in pixels
        int newHeight = 350; // desired height in pixels
        fish[0] = Bitmap.createScaledBitmap(originalFish, newWidth, newHeight, false);
        fish[1] = Bitmap.createScaledBitmap(originalFish2, newWidth, newHeight, false);

        // Load and resize the yellow ball image
        Bitmap originalYellowBall = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_ball);
        int yellowBallSize = 150; // Adjust the size as needed
        yellowBall = Bitmap.createScaledBitmap(originalYellowBall, yellowBallSize, yellowBallSize, false);

        // Load and resize the green ball image
        Bitmap originalGreenBall = BitmapFactory.decodeResource(getResources(), R.drawable.green_ball);
        int greenBallSize = 150; // Adjust the size as needed
        greenBall = Bitmap.createScaledBitmap(originalGreenBall, greenBallSize, greenBallSize, false);

        // Load and resize the red ball image
        Bitmap originalRedBall = BitmapFactory.decodeResource(getResources(), R.drawable.red_ball);
        int redBallSize = 170; // Adjust the size as needed
        redBall = Bitmap.createScaledBitmap(originalRedBall, redBallSize, redBallSize, false);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(80);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        // Resize the heart icons
        Bitmap originalRedHeart = BitmapFactory.decodeResource(getResources(), R.drawable.heart_red_rb);
        Bitmap originalWhiteHeart = BitmapFactory.decodeResource(getResources(), R.drawable.heart_white_rb);
        int heartSize = 100; // Adjust as needed
        life[0] = Bitmap.createScaledBitmap(originalRedHeart, heartSize, heartSize, false);
        life[1] = Bitmap.createScaledBitmap(originalWhiteHeart, heartSize, heartSize, false);

        fishY = 550;
        score = 0;
        lifeCounterOfFish = 3;

        win();


    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        //canvas.drawBitmap(backGround,0,0,null);

        //fishFly
        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;

        if(fishY < minFishY){
            fishY = minFishY;
        }

        if(fishY > maxFishY){
            fishY = maxFishY;
        }
        fishSpeed = fishSpeed + 2;

        if (touch){
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;
        }else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }

        yellowX = yellowX - yellowSpeed;

        // calculate the score
        if(hitBall(yellowX, yellowY)){
            score = score + 10;
            yellowX =  -100;
        }

        // Draw the yellow ball image
        if (yellowX < 0) {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

        // Draw the yellow ball image
        canvas.drawBitmap(yellowBall, yellowX, yellowY, null);

        greenX = greenX - greenSpeed;

        // calculate the score of greenBall
        if(hitBall(greenX, greenY)){
            score = score + 20;
            greenX =  -100;
        }

        // Draw the green ball image
        if (greenX < 0) {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

        // Draw the green ball image
        canvas.drawBitmap(greenBall, greenX, greenY, null);

        redX = redX - redSpeed;

        // calculate the score of redBall
        if(hitBall(redX, redY)){
            score = score - 10;
            redX =  -100;
            lifeCounterOfFish--;

            if (lifeCounterOfFish == 0){
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), Gameover.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(intent);
            }
        }

        // Draw the red ball image
        if (redX < 0) {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

        // Draw the red ball image
        canvas.drawBitmap(redBall, redX, redY, null);

        // redirect to the win activity
        if (score == 500 || score > 500) {
            win();
        }


        // display the score
        canvas.drawText("Score : " +score+"/500",60,90, scorePaint);


        // fish life score
        for (int i = 0; i<3; i++){
            int x = (int) (880 + life[0].getWidth() * 1.5*i);
            int y = 30;

            if (i < lifeCounterOfFish){
                canvas.drawBitmap(life[0], x, y, null);
            }else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
    }


    public void win() {

        if (score == 500 || score > 500){
            Toast.makeText(getContext(), "You Win", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), Win.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getContext().startActivity(intent);
        }

    }

    // fish get the ball and diaper the ball and increase the marks
    public boolean hitBall(int x, int y){

        if (fishX < x && x < (fishX + fish[0].getWidth()) &&
                fishY < y && y < (fishY + fish[0].getHeight())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN){

            // play the sound track for touch
            touchSound.start();
            touch = true;
            fishSpeed = -22;
        }

        return true;
    }
}

