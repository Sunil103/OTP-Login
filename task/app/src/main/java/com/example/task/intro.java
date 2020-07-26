package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class intro extends AppCompatActivity {
    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabLayout;
    Button login,register;
    int position = 0 ;
    TextView skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        getSupportActionBar().hide();
        tabLayout = findViewById(R.id.tab_indicator);
        skip = findViewById(R.id.skip);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        final List<ScreenItems> mList = new ArrayList<>();

        mList.add(new ScreenItems("LOGIN","Simply login to our application by registering to it first with all necessary details.",R.drawable.pic5));
        mList.add(new ScreenItems("SCAN","After logging in just scan your source and destination points after the travel instead of buying tickets.",R.drawable.pic4));
        mList.add(new ScreenItems("TRAVEL","Travel whereever you want to in the city and you can exit whenever you want in between without having condition to pay the travell fare to the destination which you choose priorily.",R.drawable.pic3));
        mList.add(new ScreenItems("PAY","Pay the travel fare once you reach your destination in whichever the payment mode you like to pay.",R.drawable.pic2));
        mList.add(new ScreenItems("HASSLE FREE","Enjoy the hassle free,time saving journey!!",R.drawable.pic1));


        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabLayout.setupWithViewPager(screenPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==mList.size()-1){
                    loadLastscreen();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                skip.setVisibility(View.VISIBLE);
                login.setVisibility(View.INVISIBLE);
                register.setVisibility(View.INVISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if(position < mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if(position == mList.size()-1){
                    loadLastscreen();
                }
            }


        });

    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(intro.this,login.class);
            startActivity(i);
        }
    });
    }
    private void loadLastscreen() {

        skip.setVisibility(View.INVISIBLE);
        login.setVisibility(View.VISIBLE);
        register.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
    }

}
