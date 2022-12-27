package com.flavored.inst_saver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Downloader extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout layout;

    // declare the slider adapter variable
    SliderAdapter sliderAdapter;

    // declare an array to contain the dots in our onboarding screen.
    TextView[] dots;

    // the button;
    Button getStartedButton, next, skip;
    Animation anim;
    int currentPostion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader);

        viewPager = (ViewPager) findViewById(R.id.slider);
        layout = (LinearLayout) findViewById(R.id.linearlayout);
        getStartedButton = (Button) findViewById(R.id.get_started_button);
        next = (Button) findViewById(R.id.next_btn);
        skip = (Button) findViewById(R.id.skip_btn);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPostion + 1);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                finish();
            }
        });

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                finish();
            }
        });

        // call the slider adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        // we then call the addDots method
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    // we then define a function to house our dots

    private void addDots(int position){
        // we indicate the number of dots we will be working with

        dots = new TextView[3];
        layout.removeAllViews();

        // we use a forloop to display the dots.

        for (int i = 0; i <dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(45);

            // add dots to the linearlayout
            layout.addView(dots[i]);
        }

        if (dots.length > 0){
            // change colors
            dots[position].setTextColor(getResources().getColor(R.color.lime));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            currentPostion = position;
            // we then implement hiding the button

            if (position == 0){
                getStartedButton.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
            }
            else if (position == 1){
                getStartedButton.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);

            }
            else {
                anim = AnimationUtils.loadAnimation(Downloader.this, R.anim.button_anim);
                getStartedButton.setAnimation(anim);
                getStartedButton.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    // define the skip and the next functions

    public void skip(){
        startActivity(new Intent(getApplicationContext(), Dashboard.class));
        finish();
    }

    public void next(){
        viewPager.setCurrentItem(currentPostion + 1);
    }
}