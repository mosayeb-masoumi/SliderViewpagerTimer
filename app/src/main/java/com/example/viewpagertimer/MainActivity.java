package com.example.viewpagertimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.example.viewpagertimer.animation.CubeInDepthTransformation;
import com.example.viewpagertimer.animation.FadeTransformation;
import com.example.viewpagertimer.animation.PopTransformation;
import com.example.viewpagertimer.viewpager.ViewPagerCustomDuration;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    CircleIndicator indicator;
    ViewPagerCustomDuration viewpager;


    int sliderCurrentPosition = 0;
    int sliderListSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewpager = findViewById(R.id.viewpager);
        indicator = findViewById(R.id.indicator);


        List<String> list = new ArrayList<>();
        list.add("tab0");
        list.add("tab1");
        list.add("tab2");
        list.add("tab3");


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);
        indicator.setViewPager(viewpager);
        viewpager.setPageTransformer(true, new CubeInDepthTransformation());


        sliderListSize = list.size();
        sliderCurrentPosition = 0;


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int positionScrolled, float positionOffset, int positionOffsetPixels) {
                sliderCurrentPosition = positionScrolled;
                startSliderTimer();
            }

            @Override
            public void onPageSelected(int position) {
//                sliderCurrentPosition = position;


            }

            @Override
            public void onPageScrollStateChanged(int state) {

                Log.i("TAG", "onPageScrollStateChanged: ");
            }
        });

    }

    final Handler sliderHandler = new Handler(Looper.getMainLooper());

    private void startSliderTimer() {

        if (sliderHandler != null) {
            // to stop counting
            sliderHandler.removeCallbacksAndMessages(null);
        }

        if (sliderCurrentPosition < sliderListSize - 1) {
            sliderCurrentPosition++;
        } else {
            sliderCurrentPosition = 0;
        }

        sliderHandler.postDelayed(() -> {
            // here you check the value of getActivity() and break up if needed
            if (this == null)
                return;
            this.runOnUiThread(() -> {

                if (sliderCurrentPosition == 0) {
                    Log.i("TAG", "run: ");
                    viewpager.setScrollDurationFactor(0.0);
                } else {
                    viewpager.setScrollDurationFactor(8);
                }


                viewpager.setCurrentItem(sliderCurrentPosition);
            });

        }, 3000);
    }
}