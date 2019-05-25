package com.sampletext.langfocuses;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class DeckActivity extends AppCompatActivity {

    int _displayedCardIndex = 0;

    Button _deckChangeButton[] = new Button[5];

    MyFragmentPagerAdapter _pagerAdapter;

    Drawable _deckChangeButtons_Overlay;
    //region btnBackOnClickListener
    View.OnClickListener btnBackOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            finish();
        }
    };
    //endregion
    private int _displayedDeckId;
    private SeekBar _mainSeekBar;
    private ViewPager _mainPager;
    //region btnBackOnTouchListener
    private View.OnTouchListener btnBackOnTouchListener = new View.OnTouchListener() {

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
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
            if (lastPos != position) // checking to prevent recursive calls from seekbar to pager and backwards
            {
                lastPos = position;
                _displayedCardIndex = position;
                _mainSeekBar.setProgress(position);
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
                _displayedCardIndex = i;
                _mainPager.setCurrentItem(i, true);
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
    private TextView _deckHeader;
    //endregion
    //region deckClickListener
    final View.OnClickListener btnDeckClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            _displayedDeckId = Integer.parseInt(v.getTag().toString()) - 1;
            initializeDeck();
        }
    };

    private void setLocalPageIndex(int index) {
        _mainSeekBar.setProgress(index);
        _mainPager.setCurrentItem(index, true);
    }

    void findAndSetupDeckButtons() {
        _deckChangeButton[0] = findViewById(R.id.btn_deck1);
        _deckChangeButton[1] = findViewById(R.id.btn_deck2);
        _deckChangeButton[2] = findViewById(R.id.btn_deck3);
        _deckChangeButton[3] = findViewById(R.id.btn_deck4);
        _deckChangeButton[4] = findViewById(R.id.btn_deck5);

        for (Button a_deckChangeButton : _deckChangeButton) {
            a_deckChangeButton.setOnClickListener(btnDeckClickListener);
        }
    }

    void loadDeckButtonsOverlay() {
        _deckChangeButtons_Overlay = getResources().getDrawable(R.drawable.card_change_bw_on3);
    }

    void launchDeckFromIntent() {
        int deck_id = getIntent().getIntExtra("deck_id", -1);
        if (deck_id == -1) {
            Toast.makeText(this, "Deck not found", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            _displayedDeckId = deck_id;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_deck);
        } catch (OutOfMemoryError e) {
            Toast.makeText(getApplicationContext(), "Not enough memory", Toast.LENGTH_SHORT).show();
            finish();
        }

        Static.SetPortrait(this);

        Static.SetViewScale(findViewById(R.id.deckActivityRoot));

        findAndSetupDeckButtons();
        loadDeckButtonsOverlay();

        PagerTabStrip mainPagerTabStrip = findViewById(R.id.mainPagerTabStrip);

        _mainSeekBar = findViewById(R.id.mainSeekBar);
        _mainSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        _mainPager = findViewById(R.id.mainPager);
        _mainPager.addOnPageChangeListener(pageChangeListener);

        _pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        _deckHeader = findViewById(R.id.deckNameHeaderText);

        launchDeckFromIntent();

        initializeDeck();

        //устанавливать обязательно после загрузки колоды
        _mainPager.setAdapter(_pagerAdapter);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.getBackground().setColorFilter(Color.parseColor("#87704A"), PorterDuff.Mode.SRC_ATOP);
        btnBack.setOnTouchListener(btnBackOnTouchListener);
        btnBack.setOnClickListener(btnBackOnClickListener);


        Static.fitText(_deckHeader);
        if (Static.DiagonalInches > 7f) {
            mainPagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20 * Static.ScaleFactor);
        }
    }

    private void initializeDeck() {
        try {
            for (Button btnDeck : _deckChangeButton) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnDeck.setForeground(null);
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                _deckChangeButton[_displayedDeckId].setForeground(_deckChangeButtons_Overlay);
            }

            //проверяем перед сменой колоды, чтобы 16 карта не выскочила на другие
            if (_displayedCardIndex == 15) {
                _displayedCardIndex = 14;
            }
            setLocalPageIndex(_displayedCardIndex);

            Deck deck = DecksContainer.getDeck(_displayedDeckId);

            _pagerAdapter.set_deck(deck);
            _pagerAdapter.notifyDataSetChanged();

            assert deck != null;
            _deckHeader.setText(deck.getHeader());
            _deckHeader.setTextColor(deck.getDeckColor());

            _mainSeekBar.getThumb().setColorFilter(
                    deck.getDeckColor(), PorterDuff.Mode.SRC_ATOP);
            _mainSeekBar.getProgressDrawable().setColorFilter(
                    deck.getDeckColor(), PorterDuff.Mode.SRC_ATOP);
            _mainSeekBar.setMax(deck.getCardsCount() - 1);


        } catch (Exception ignored) {

        }

    }
}
