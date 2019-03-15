package com.sampletext.langfocuses;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

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

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView mContent = findViewById(R.id.about_content);
        TextView mHeader = findViewById(R.id.about_header);
        mBtnBack = findViewById(R.id.btn_back);

        if (Static.DiagonalInches >= 6.5f) {
            mContent.setTextSize(mContent.getTextSize() * Static.ScaleFactor);
            mHeader.setTextSize(mHeader.getTextSize() * Static.ScaleFactor);
        }

        mBtnBack.getBackground().setColorFilter(Color.parseColor("#574435"), PorterDuff.Mode.SRC_ATOP);
        mBtnBack.setOnTouchListener(btnBackOnTouchListener);
        mBtnBack.setOnClickListener(btnBackOnClickListener);
    }
}
