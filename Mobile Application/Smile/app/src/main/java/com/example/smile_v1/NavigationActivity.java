package com.example.smile_v1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class NavigationActivity extends AppCompatActivity {

    ViewPager sliderViewPager;
    LinearLayout dotIndicator;
    ViewPagerAdapter viewPagerAdapter;
    Button backButton, skipButton, nextButton;
    TextView[] dots;

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {

            setDotIndicator(position);

            if (position > 0){
                backButton.setVisibility(View.VISIBLE);
            }else{
                backButton.setVisibility(View.INVISIBLE);
            }

            if (position == 4){
                nextButton.setText("Finish");
            }else {
                nextButton.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);

        backButton = findViewById(R.id.backBtn);
        skipButton = findViewById(R.id.skipBtn);
        nextButton = findViewById(R.id.nextBtn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) >0){
                    sliderViewPager.setCurrentItem(getItem(-1), true);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) < 4){
                    sliderViewPager.setCurrentItem(getItem(1), true);
                }else {
                    Intent intent = new Intent(NavigationActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sliderViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        dotIndicator = (LinearLayout) findViewById(R.id.dotIndicator);

        viewPagerAdapter = new ViewPagerAdapter(NavigationActivity.this);
        sliderViewPager.setAdapter(viewPagerAdapter);

        setDotIndicator(0);
        sliderViewPager.addOnPageChangeListener(viewPagerListener);

    }

    public void setDotIndicator(int position){
        dots = new TextView[5];
        dotIndicator.removeAllViews();

        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.gray, getApplicationContext().getTheme()));
            dotIndicator.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.splash_screen_blue, getApplicationContext().getTheme()));
    }

    private int getItem(int i){
        return sliderViewPager.getCurrentItem() + i;
    }
}