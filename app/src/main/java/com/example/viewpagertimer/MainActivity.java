package com.example.viewpagertimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
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
    int position =0;
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
//      viewpager.setPageTransformer(true, new PopTransformation());


        int totalSize = list.size();



        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
                                  @Override
                                  public void run() {

                                      runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {

                                              if(position==0){
                                                  Log.i("TAG", "run: ");
                                                  viewpager.setScrollDurationFactor(0.7);
                                              }else{
                                                  viewpager.setScrollDurationFactor(5);
                                              }

                                              viewpager.setCurrentItem(position);
                                          }
                                      });

                                      position++;
                                      if(position >= (totalSize)){
                                          position=0;
                                      }
                                  }
                              },
//Set how long before to start calling the TimerTask (in milliseconds)
                0,
//Set the amount of time between each execution (in milliseconds)
                3000);



        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int positionScrolled, float positionOffset, int positionOffsetPixels) {
               position = positionScrolled;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}