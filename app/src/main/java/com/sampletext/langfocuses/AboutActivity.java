package com.sampletext.langfocuses;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {


    //region btnBackOnClickListener
    View.OnClickListener btnBackOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            finish();
        }
    };
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_about);
        } catch (OutOfMemoryError e) {
            Toast.makeText(getApplicationContext(), "Not enough memory", Toast.LENGTH_SHORT).show();
            finish();
        }

        Static.SetPortrait(this);

        Static.SetViewScale(findViewById(R.id.about_root));

        TextView mContent = findViewById(R.id.about_content);
        TextView mHeader = findViewById(R.id.about_header);
        Button mBtnBack = findViewById(R.id.btn_back);

        Static.fitText(mContent);
        Static.fitText(mHeader);

        //предустанавливаем цвет кнопки назад
        mBtnBack.getBackground().setColorFilter(Color.parseColor("#574435"), PorterDuff.Mode.SRC_ATOP);
        mBtnBack.setOnTouchListener(btnBackOnTouchListener);
        mBtnBack.setOnClickListener(btnBackOnClickListener);
    }
}
