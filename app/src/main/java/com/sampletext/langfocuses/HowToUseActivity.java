package com.sampletext.langfocuses;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HowToUseActivity extends AppCompatActivity {

    private TextView mInfoText;

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
                    mBtnBack.getBackground().setColorFilter(Color.parseColor("#574435"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    mBtnBack.getBackground().setColorFilter(Color.parseColor("#574435"), PorterDuff.Mode.SRC_ATOP);
                    break;
                default:
            }
            return false;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);
        mInfoText = findViewById(R.id.howtouse_content);
        TextView mHeaderText = findViewById(R.id.howtouse_header);
        mBtnBack = findViewById(R.id.btn_back);

        if (Static.DiagonalInches >= 6.5f) {
            mHeaderText.setTextSize(mHeaderText.getTextSize() * Static.ScaleFactor);
            mInfoText.setTextSize(mInfoText.getTextSize() * Static.ScaleFactor);
        }

        mBtnBack.getBackground().setColorFilter(Color.parseColor("#574435"), PorterDuff.Mode.SRC_ATOP);
        mBtnBack.setOnTouchListener(btnBackOnTouchListener);
        mBtnBack.setOnClickListener(btnBackOnClickListener);
    }
}
