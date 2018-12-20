package com.sampletext.langfocuses;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeckActivity extends AppCompatActivity {

    List<Integer> _displayedCardIndexes = new ArrayList<>();

    MyFragmentPagerAdapter pagerAdapter;

    //region btnBackOnClickListener
    View.OnClickListener btnBackOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            _displayedCardIndexes.clear();
            finish();
        }
    };

    private int _displayedDeckId;

    private PagerTabStrip mMainPagerTabStrip;

    private SeekBar mMainSeekBar;

    private ViewPager mMainPager;

    private Button mBtnBack;
    //endregion

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
    //endregion

    //region btnChangeDeckTouchListener
    private View.OnTouchListener btnChangeDeckTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_HOVER_ENTER:
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setColorFilter(Color.parseColor("#F5888888"), PorterDuff.Mode.SRC_ATOP);
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

    //region pageChangeListener
    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

        int lastPos = 0;//last page index

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (lastPos != position) // checking to prevent recursive calls from seekbar to pager and backwords
            {
                lastPos = position;
                mMainSeekBar.setProgress(position);
                _displayedCardIndexes.set(_displayedDeckId, position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    //endregion

    //region seekBarChangeListener
    private OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {

        int lastPos = 0; // last page index

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (lastPos != i)// checking to prevent recursive calls from seekbar to pager and backwords
            {
                lastPos = i;
                mMainPager.setCurrentItem(i, true);
                _displayedCardIndexes.set(_displayedDeckId, i);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    //endregion

    private TextView mDeckHeader;

    private LinearLayout mBtnDeckUp;

    private LinearLayout mBtnDeckDown;

    //region btnDeckDownOnClickListener
    View.OnClickListener btnDeckDownOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Deck Down", Toast.LENGTH_SHORT).show();

            if (_displayedDeckId < DecksContainer.getDecksCount() - 1) {
                _displayedDeckId++;
                initializeDeck();
            }
        }
    };

    //region btnDeckUpOnClickListener
    View.OnClickListener btnDeckUpOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Deck Up", Toast.LENGTH_SHORT).show();
            if (_displayedDeckId > 0) {
                _displayedDeckId--;
                initializeDeck();
            }
        }
    };
    //endregion
    private void setLocalPageIndex(int index) {
        mMainSeekBar.setProgress(index);
        mMainPager.setCurrentItem(index, true);
    }
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);

        mMainPagerTabStrip = findViewById(R.id.mainPagerTabStrip);
        mMainSeekBar = findViewById(R.id.mainSeekBar);
        mMainPager = findViewById(R.id.mainPager);
        mDeckHeader = findViewById(R.id.deckHeader);
        mBtnDeckUp = findViewById(R.id.btn_deck_up);
        mBtnDeckDown = findViewById(R.id.btn_deck_down);

        mBtnDeckUp.setClickable(true);
        mBtnDeckDown.setClickable(true);
        mBtnDeckUp.setOnClickListener(btnDeckUpOnClickListener);
        mBtnDeckDown.setOnClickListener(btnDeckDownOnClickListener);

        mBtnDeckUp.setOnTouchListener(btnChangeDeckTouchListener);
        mBtnDeckDown.setOnTouchListener(btnChangeDeckTouchListener);

        mMainPager.addOnPageChangeListener(pageChangeListener);

        int deck_id = this.getIntent().getIntExtra("deck_id", -1);
        if (deck_id == -1) {
            Toast.makeText(this, "Deck not found", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            for (int i = 0; i < DecksContainer.getDecksCount(); i++) {
                _displayedCardIndexes.add(0);
            }
            _displayedDeckId = deck_id;
            initializeDeck();
        }

        mBtnBack = findViewById(R.id.btn_back);
        mBtnBack.getBackground().setColorFilter(Color.parseColor("#F5BBBBBB"), PorterDuff.Mode.SRC_ATOP);
        mBtnBack.setOnTouchListener(btnBackOnTouchListener);
        mBtnBack.setOnClickListener(btnBackOnClickListener);

        mMainSeekBar.setMax((pagerAdapter.getCount() - 1));
        mMainSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }
    private void initializeDeck() {
        Deck deck = DecksContainer.getDeck(_displayedDeckId);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.set_deck(deck);
        mMainPager.setAdapter(pagerAdapter);
        mDeckHeader.setText(deck.getHeader());
        mDeckHeader.setTextColor(deck.getDeckColor());

        mMainSeekBar.getThumb().setColorFilter(
                deck.getDeckColor(), PorterDuff.Mode.SRC_ATOP);
        mMainSeekBar.getProgressDrawable().setColorFilter(
                deck.getDeckColor(), PorterDuff.Mode.SRC_ATOP);

        setLocalPageIndex(_displayedCardIndexes.get(_displayedDeckId));

        if (_displayedDeckId == 0) {
            mBtnDeckUp.setAlpha(0.5f);
            mBtnDeckUp.setOnTouchListener(null);
        }
        else {
            mBtnDeckUp.setAlpha(1f);
            mBtnDeckUp.setOnTouchListener(btnChangeDeckTouchListener);
        }
        if (_displayedDeckId == DecksContainer.getDecksCount() - 1) {
            mBtnDeckDown.setAlpha(0.5f);
            mBtnDeckDown.setOnTouchListener(null);
        }
        else {
            mBtnDeckDown.setAlpha(1f);
            mBtnDeckDown.setOnTouchListener(btnChangeDeckTouchListener);
        }
    }
}
