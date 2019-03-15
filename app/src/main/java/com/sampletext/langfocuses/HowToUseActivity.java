package com.sampletext.langfocuses;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HowToUseActivity extends AppCompatActivity {

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
                    v.getBackground().setColorFilter(Color.parseColor("#574435"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    v.getBackground().setColorFilter(Color.parseColor("#574435"), PorterDuff.Mode.SRC_ATOP);
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

        Static.SetPortrait(this);

        View root = findViewById(R.id.howtouse_root);
        Static.SetViewScale(root);

        TextView infoText = findViewById(R.id.howtouse_content);
        TextView headerText = findViewById(R.id.howtouse_header);
        Button btnBack = findViewById(R.id.btn_back);

        if (Static.DiagonalInches >= 6.5f) {
            headerText.setTextSize(headerText.getTextSize() * Static.ScaleFactor);
            infoText.setTextSize(infoText.getTextSize() * Static.ScaleFactor);
        }

        btnBack.getBackground().setColorFilter(Color.parseColor("#574435"), PorterDuff.Mode.SRC_ATOP);
        btnBack.setOnTouchListener(btnBackOnTouchListener);
        btnBack.setOnClickListener(btnBackOnClickListener);
    }
}
