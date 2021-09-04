package com.example.viewpagertimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.viewpagertimer.animation.CubeOutDepthTransformation;
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
    
    Button btn_dialog;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_dialog = findViewById(R.id.btn_dialog);
        root = findViewById(R.id.root);

        
        viewpager = findViewById(R.id.viewpager);
        indicator = findViewById(R.id.indicator);


        list.add("tab0");
        list.add("tab1");
        list.add("tab2");
        list.add("tab3");


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);
        indicator.setViewPager(viewpager);
      viewpager.setPageTransformer(true, new CubeOutDepthTransformation()); // to set animation
        totalSize = list.size();




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
        
        
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnimatedDialog();
            }
        });

    }

    private void showAnimatedDialog() {

        DialogFactory dialog = new DialogFactory(this);
        dialog.createAnimateDialog(new DialogFactory.DialogFactoryInteraction() {
            @Override
            public void onAcceptButtonClicked(String... strings) {

            }

            @Override
            public void onDeniedButtonClicked(boolean cancel_dialog) {

            }
        } , root);
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