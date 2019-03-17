package com.sampletext.langfocuses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Logo2Activity extends Activity {

    ImageView _logoImageView;

    TextView _loadingText;

    int _dots = 0;

    Animation.AnimationListener animationListener = new Animation.AnimationListener() {

        Intent intent;

        @Override
        public void onAnimationStart(Animation animation) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            startActivity(intent);
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    void setupAnimation(ImageView logoImageView) {
        float animScaleStart = 0.95f;
        float animScaleEnd = 1.00f;
        float animRelativeTo = 0.5f;

        Animation anim = new ScaleAnimation(
                animScaleStart, animScaleEnd,
                animScaleStart, animScaleEnd,
                Animation.RELATIVE_TO_SELF, animRelativeTo,
                Animation.RELATIVE_TO_SELF, animRelativeTo);
        anim.setFillAfter(true); //оставить результат, не сбрасывать на начало
        anim.setDuration(2000);
        anim.setAnimationListener(animationListener);

        logoImageView.setAnimation(anim);
    }


    private void launchDotsThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder dotsStr = new StringBuilder(4);
                            for (int i = 0; i < _dots; i++) {
                                dotsStr.append('.');
                            }
                            _loadingText.setText(String.format("Loading%s", dotsStr));
                        }
                    });
                    try {
                        Thread.sleep(300);
                        _dots++;
                        _dots %= 4;
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo2);

        Static.SetPortrait(this);

        Static.SetViewScale(findViewById(R.id.logo2_root));

        _logoImageView = findViewById(R.id.logoImageView2);
        _loadingText = findViewById(R.id.loadingText);

        Static.fitText(_loadingText);

        setupAnimation(_logoImageView);

        launchDotsThread();
    }
}
