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
            int      position = (int) v.getTag();
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
                    v.getBackground().setColorFilter(Color.parseColor("#F5888888"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_HOVER_EXIT:
                case MotionEvent.ACTION_UP:
                    mBtnBack.getBackground().setColorFilter(Color.parseColor("#F5BBBBBB"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    mBtnBack.getBackground().setColorFilter(Color.parseColor("#F5BBBBBB"), PorterDuff.Mode.SRC_ATOP);
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
        mBtnBack.getBackground().setColorFilter(Color.parseColor("#F5BBBBBB"), PorterDuff.Mode.SRC_ATOP);
        mBtnBack.setOnTouchListener(btnBackOnTouchListener);
        mBtnBack.setOnClickListener(btnBackOnClickListener);

        mDecksContainer = findViewById(R.id.decks_container);

        for (int i = 0; i < DecksContainer.getDecksCount(); i++) {
            createDeck(this, mDecksContainer, i);
        }
    }
    private void createDeck(Context context, ViewGroup parent, int deckIndex) {
        FrameLayout              deckFrame    = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (100 * Static.Density));

        if (parent.getChildCount() != 0) {
            layoutParams.topMargin = (int) (10 * Static.Density);
        }

        deckFrame.setLayoutParams(layoutParams);

        LinearLayout deckLayout = new LinearLayout(context);
        layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        deckLayout.setLayoutParams(layoutParams);
        deckLayout.setBackground(context.getDrawable(R.drawable.deck));
        deckLayout.setOrientation(LinearLayout.VERTICAL);
        deckLayout.setClickable(true);
        deckLayout.setOnTouchListener(deckTouchListener);
        deckLayout.setTag(deckIndex);
        deckLayout.setOnClickListener(deckClickListener);
        deckFrame.addView(deckLayout);

        TextView deckHeaderText = new TextView(this);
        layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        deckHeaderText.setLayoutParams(layoutParams);

        deckHeaderText.setGravity(Gravity.CENTER);
        deckHeaderText.setText(DecksContainer.getDeck(deckIndex).getHeader());
        deckHeaderText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
        deckHeaderText.setTextColor(DecksContainer.getDeck(deckIndex).getDeckColor());

        deckLayout.addView(deckHeaderText);

        parent.addView(deckFrame);
    }
}
