package com.sampletext.langfocuses;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectDeckActivity extends AppCompatActivity {

    //region deckClickListener
    View.OnClickListener deckClickListener = v -> {
        int position = Integer.parseInt(v.getTag().toString());
        Log.e("EGOP", "DECK CLICK LISTENER");
        Intent intent = new Intent(SelectDeckActivity.this, DeckActivity.class);
        intent.putExtra("deck_id", position);
        Log.e("EGOP", "STARTING ACTIVITY");
        startActivity(intent);
        Log.e("EGOP", "Started activity");
    };
    //endregion

    //region btnBackOnClickListener
    View.OnClickListener btnBackOnClickListener = v -> finish();
    //endregion

    //region deckTouchListener
    private View.OnTouchListener deckTouchListener = (v, event) -> {
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
    };
    //endregion

    //region btnBackOnTouchListener
    private View.OnTouchListener btnBackOnTouchListener = (v, event) -> {
        switch (event.getAction()) {
            case MotionEvent.ACTION_HOVER_ENTER:
            case MotionEvent.ACTION_DOWN:
                v.getBackground().setColorFilter(Color.parseColor("#e6c287"), PorterDuff.Mode.SRC_ATOP);
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
            case MotionEvent.ACTION_UP:
                v.getBackground().setColorFilter(Color.parseColor("#87704A"), PorterDuff.Mode.SRC_ATOP);
                break;
            case MotionEvent.ACTION_CANCEL:
                v.getBackground().setColorFilter(Color.parseColor("#87704A"), PorterDuff.Mode.SRC_ATOP);
                break;
            default:
        }
        return false;
    };
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_select_deck);
        } catch (OutOfMemoryError e) {
            Toast.makeText(getApplicationContext(), "Not enough memory", Toast.LENGTH_SHORT).show();
            finish();
        }
        Static.SetPortrait(this);

        Static.SetViewScale(findViewById(R.id.selectdeck_root));

        TextView mHeader = findViewById(R.id.selectdeck_header);

        Button mBtnBack = findViewById(R.id.btn_back);
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

        Static.fitText(mHeader);
        Static.fitText(deck0button);
        Static.fitText(deck1button);
        Static.fitText(deck2button);
        Static.fitText(deck3button);
        Static.fitText(deck4button);
    }
}
