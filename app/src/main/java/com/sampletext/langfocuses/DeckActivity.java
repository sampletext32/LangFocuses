package com.sampletext.langfocuses;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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

    int _displayedCardIndex = 0;
    LinearLayout _deckActivityRoot;

    Button btnsDeck[] = new Button[5];

    MyFragmentPagerAdapter pagerAdapter;

    Drawable btnsDeck_Overlay;

    //region btnBackOnClickListener
    View.OnClickListener btnBackOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
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
    //endregion

    //region deckClickListener
    View.OnClickListener btnDeckClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int position = Integer.parseInt(v.getTag().toString()) - 1;
            _displayedDeckId = position;
            initializeDeck();
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
            pagerAdapter.notifyDataSetChanged();
            if (lastPos != position) // checking to prevent recursive calls from seekbar to pager and backwards
            {
                lastPos = position;
                mMainSeekBar.setProgress(position);
                _displayedCardIndex = position;
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
                _displayedCardIndex = i;
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

    private void setLocalPageIndex(int index) {
        mMainSeekBar.setProgress(index);
        mMainPager.setCurrentItem(index, true);
    }

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);

        _deckActivityRoot = findViewById(R.id.deckActivityRoot);

        btnsDeck[0] = findViewById(R.id.btn_deck1);
        btnsDeck[1] = findViewById(R.id.btn_deck2);
        btnsDeck[2] = findViewById(R.id.btn_deck3);
        btnsDeck[3] = findViewById(R.id.btn_deck4);
        btnsDeck[4] = findViewById(R.id.btn_deck5);

        for (int i = 0; i < btnsDeck.length; i++) {
            btnsDeck[i].setOnClickListener(btnDeckClickListener);
        }

        btnsDeck_Overlay = getResources().getDrawable(R.drawable.card_change_bw_on3);

        //region !!!ПРОТЕСТИТЬ НА ТЕЛЕФОНЕ!!!
        /*if(_deckActivityRoot.getHeight() > 1920)
        {
            _deckActivityRoot.setScaleY(1920f / _deckActivityRoot.getHeight());
        }*/
        //endregion


        mMainPagerTabStrip = findViewById(R.id.mainPagerTabStrip);
        mMainSeekBar = findViewById(R.id.mainSeekBar);
        mMainPager = findViewById(R.id.mainPager);
        mDeckHeader = findViewById(R.id.deckNameHeaderText);

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mMainPager.addOnPageChangeListener(pageChangeListener);

        int deck_id = this.getIntent().getIntExtra("deck_id", -1);
        if (deck_id == -1) {
            Toast.makeText(this, "Deck not found", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            _displayedDeckId = deck_id;
            initializeDeck();
            mMainPager.setAdapter(pagerAdapter);
        }

        mBtnBack = findViewById(R.id.btn_back);
        mBtnBack.getBackground().setColorFilter(Color.parseColor("#87704A"), PorterDuff.Mode.SRC_ATOP);
        mBtnBack.setOnTouchListener(btnBackOnTouchListener);
        mBtnBack.setOnClickListener(btnBackOnClickListener);

        mMainSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    private void initializeDeck() {
        try {
            for (int i = 0; i < btnsDeck.length; i++) {
                btnsDeck[i].setForeground(null);
            }
            btnsDeck[_displayedDeckId].setForeground(btnsDeck_Overlay);

            //проверяем перед сменой колоды, чтобы 16 карта не выскочила на другие
            if(_displayedCardIndex == 15)
            {
                _displayedCardIndex = 14;
            }
            setLocalPageIndex(_displayedCardIndex);


            Deck deck = DecksContainer.getDeck(_displayedDeckId);



            pagerAdapter.set_deck(deck);
            pagerAdapter.notifyDataSetChanged();

            mDeckHeader.setText(deck.getHeader());
            mDeckHeader.setTextColor(deck.getDeckColor());

            mMainSeekBar.getThumb().setColorFilter(
                    deck.getDeckColor(), PorterDuff.Mode.SRC_ATOP);
            mMainSeekBar.getProgressDrawable().setColorFilter(
                    deck.getDeckColor(), PorterDuff.Mode.SRC_ATOP);
            mMainSeekBar.setMax((pagerAdapter.getCount() - 1));


        } catch (Exception ignored) {
            Toast.makeText(getApplicationContext(), ignored.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
