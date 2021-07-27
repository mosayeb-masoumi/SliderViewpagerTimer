package com.example.viewpagertimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.example.viewpagertimer.viewpager.ViewPagerCustomDuration;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    CircleIndicator indicator;
    ViewPagerCustomDuration viewpager;
    int sliderCurrentPosition =0;
    int totalSize = 0;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = findViewById(R.id.viewpager);
        indicator = findViewById(R.id.indicator);


        list.add("tab0");
        list.add("tab1");
        list.add("tab2");
        list.add("tab3");


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);
        indicator.setViewPager(viewpager);
//      viewpager.setPageTransformer(true, new PopTransformation()); // to set animation
        totalSize = list.size();



//        Timer t = new Timer();
//        t.scheduleAtFixedRate(new TimerTask() {
//                                  @Override
//                                  public void run() {
//
//                                      runOnUiThread(new Runnable() {
//                                          @Override
//                                          public void run() {
//
//                                              if(sliderCurrentPosition==0){
//                                                  Log.i("TAG", "run: ");
//                                                  viewpager.setScrollDurationFactor(0.7);
//                                              }else{
//                                                  viewpager.setScrollDurationFactor(5);
//                                              }
//
//                                              viewpager.setCurrentItem(sliderCurrentPosition);
//                                          }
//                                      });
//
//                                      sliderCurrentPosition++;
//                                      if(sliderCurrentPosition >= (totalSize)){
//                                          sliderCurrentPosition=0;
//                                      }
//                                  }
//                              },
////Set how long before to start calling the TimerTask (in milliseconds)
//                0,
////Set the amount of time between each execution (in milliseconds)
//                3000);



        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int positionScrolled, float positionOffset, int positionOffsetPixels) {
                sliderCurrentPosition = positionScrolled;
                startSliderTimer();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    final Handler sliderHandler = new Handler(Looper.getMainLooper());
    private void startSliderTimer() {

        if(sliderHandler != null){
            // to stop counting
            sliderHandler.removeCallbacksAndMessages(null);
        }

        if(sliderCurrentPosition < totalSize - 1){
            sliderCurrentPosition++;
        }else{
            sliderCurrentPosition = 0;
        }

        sliderHandler.postDelayed(() -> {
            // here you check the value of getActivity() and break up if needed
            if (MainActivity.this == null)
                return;
            MainActivity.this.runOnUiThread(() -> {

                if (sliderCurrentPosition == 0) {
                    Log.i("TAG", "run: ");
                    viewpager.setScrollDurationFactor(0.0);
                } else {
                    viewpager.setScrollDurationFactor(3);
                }


                viewpager.setCurrentItem(sliderCurrentPosition);
            });

        }, 3000);
    }
}