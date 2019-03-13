package com.sampletext.langfocuses;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SelectDeckActivity extends AppCompatActivity {

    //region deckClickListener
    View.OnClickListener deckClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int      position = Integer.parseInt(v.getTag().toString());
            Intent   intent   = new Intent(SelectDeckActivity.this, DeckActivity.class);
            intent.putExtra("deck_id", position);
            startActivity(intent);
        }
    };
    //endregion

    //region btnBackOnClickListener
    View.OnClickListener btnBackOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            finish();
        }
    };
    //endregion

    //region deckTouchListener
    private View.OnTouchListener deckTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_HOVER_ENTER:
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setColorFilter(Color.parseColor("#BBBBBB"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_HOVER_EXIT:
                case MotionEvent.ACTION_UP:
                    v.getBackground().clearColorFilter();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    v.getBackground().clearColorFilter();
                    break;
                default:
            }
            return false;
        }
    };
    //endregion

    private Button mBtnBack;

    //region btnBackOnTouchListener
    private View.OnTouchListener btnBackOnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_HOVER_ENTER:
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setColorFilter(Color.parseColor("#e6c287"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_HOVER_EXIT:
                case MotionEvent.ACTION_UP:
                    mBtnBack.getBackground().setColorFilter(Color.parseColor("#87704A"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    mBtnBack.getBackground().setColorFilter(Color.parseColor("#87704A"), PorterDuff.Mode.SRC_ATOP);
                    break;
                default:
            }
            return false;
        }
    };

    private LinearLayout mDecksContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_deck);

        mBtnBack = findViewById(R.id.btn_back);
        mBtnBack.getBackground().setColorFilter(Color.parseColor("#87704A"), PorterDuff.Mode.SRC_ATOP);
        mBtnBack.setOnTouchListener(btnBackOnTouchListener);
        mBtnBack.setOnClickListener(btnBackOnClickListener);

        Button deck0button = findViewById(R.id.button_deck_0);
        deck0button.setOnTouchListener(deckTouchListener);
        deck0button.setOnClickListener(deckClickListener);

        Button deck1button = findViewById(R.id.button_deck_1);
        deck1button.setOnTouchListener(deckTouchListener);
        deck1button.setOnClickListener(deckClickListener);

        Button deck2button = findViewById(R.id.button_deck_2);
        deck2button.setOnTouchListener(deckTouchListener);
        deck2button.setOnClickListener(deckClickListener);

        Button deck3button = findViewById(R.id.button_deck_3);
        deck3button.setOnTouchListener(deckTouchListener);
        deck3button.setOnClickListener(deckClickListener);

        Button deck4button = findViewById(R.id.button_deck_4);
        deck4button.setOnTouchListener(deckTouchListener);
        deck4button.setOnClickListener(deckClickListener);
    }
}
