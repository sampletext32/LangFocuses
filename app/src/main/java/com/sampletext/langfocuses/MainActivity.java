package com.sampletext.langfocuses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private Button mBtnChooseDeck;

    private Button mBtnAbout;

    private ImageView mBackgroundImageView;

    private Button mBtnHowToUse;


    View.OnTouchListener btn_Highlight_OnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_HOVER_ENTER:
                case MotionEvent.ACTION_DOWN:
                    view.getBackground().setColorFilter(Color.parseColor("#F5BBBBBB"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_HOVER_EXIT:
                case MotionEvent.ACTION_UP:
                    view.getBackground().setColorFilter(Color.parseColor("#F5FFFFFF"), PorterDuff.Mode.SRC_ATOP);
                    break;

                default:
            }
            return false;
        }
    };

    private View.OnClickListener BtnHowToUse_OnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), HowToUseActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener BtnChooseDeck_OnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), SelectDeckActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener BtnAbout_OnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnChooseDeck = findViewById(R.id.btn_choose_deck);
        mBtnAbout = findViewById(R.id.btn_about);
        mBackgroundImageView = findViewById(R.id.backgroundImageView);
        mBtnHowToUse = findViewById(R.id.btn_how_to_use);

        mBtnHowToUse.setOnTouchListener(btn_Highlight_OnTouchListener);
        mBtnChooseDeck.setOnTouchListener(btn_Highlight_OnTouchListener);
        mBtnAbout.setOnTouchListener(btn_Highlight_OnTouchListener);

        mBtnHowToUse.setOnClickListener(BtnHowToUse_OnClickListener);
        mBtnChooseDeck.setOnClickListener(BtnChooseDeck_OnClickListener);
        mBtnAbout.setOnClickListener(BtnAbout_OnClickListener);
    }
}

