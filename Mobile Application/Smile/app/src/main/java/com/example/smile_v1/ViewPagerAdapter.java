package com.example.smile_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    int slideAllImages[] = {R.drawable.os_anxiety_test, R.drawable.os_depression_test, R.drawable.os_activities, R.drawable.os_chat_with_counselor, R.drawable.os_call};
    int slideAllTitle[] = {R.string.screen1, R.string.screen2, R.string.screen3, R.string.screen4, R.string.screen5};
    int slideAllDesc[] = {R.string.screen1desc, R.string.screen2desc, R.string.screen3desc, R.string.screen4desc, R.string.screen5desc};


    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slideAllTitle.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_screen, container, false);

        ImageView sliderImage = (ImageView) view.findViewById(R.id.sliderImage);
        TextView sliderTitle = (TextView) view.findViewById(R.id.sliderTitle);
        TextView sliderDesc = (TextView) view.findViewById(R.id.sliderDesc);

        sliderImage.setImageResource(slideAllImages[position]);
        sliderTitle.setText(this.slideAllTitle[position]);
        sliderDesc.setText(this.slideAllDesc[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
