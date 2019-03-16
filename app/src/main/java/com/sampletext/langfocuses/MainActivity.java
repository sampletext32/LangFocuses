package com.sampletext.langfocuses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    View.OnTouchListener btn_Highlight_OnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_HOVER_ENTER:
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setColorFilter(Color.parseColor("#F5BBBBBB"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_HOVER_EXIT:
                case MotionEvent.ACTION_UP:
                    v.getBackground().clearColorFilter();
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Static.SetPortrait(this);

        Static.SetViewScale(findViewById(R.id.main_root));

        TextView header_antifocusy = findViewById(R.id.main_header_antifocusy);
        TextView header_yazyka = findViewById(R.id.main_header_yazyka);

        Button _btnChooseDeck = findViewById(R.id.btn_choose_deck);
        Button _btnAbout = findViewById(R.id.btn_about);
        Button _btnHowToUse = findViewById(R.id.btn_how_to_use);

        if (Static.DiagonalInches >= 6.5f) {
            header_antifocusy.setTextSize(header_antifocusy.getTextSize() * Static.ScaleFactor);
            header_yazyka.setTextSize(header_yazyka.getTextSize() * Static.ScaleFactor);
            _btnAbout.setTextSize(_btnAbout.getTextSize() * Static.ScaleFactor);
            _btnChooseDeck.setTextSize(_btnChooseDeck.getTextSize() * Static.ScaleFactor);
            _btnHowToUse.setTextSize(_btnHowToUse.getTextSize() * Static.ScaleFactor);
        }

        _btnHowToUse.setOnTouchListener(btn_Highlight_OnTouchListener);
        _btnChooseDeck.setOnTouchListener(btn_Highlight_OnTouchListener);
        _btnAbout.setOnTouchListener(btn_Highlight_OnTouchListener);

        _btnHowToUse.setOnClickListener(BtnHowToUse_OnClickListener);
        _btnChooseDeck.setOnClickListener(BtnChooseDeck_OnClickListener);
        _btnAbout.setOnClickListener(BtnAbout_OnClickListener);
    }
}

