package com.sampletext.langfocuses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class LogoActivity extends Activity {

    Animation.AnimationListener animationListener = new Animation.AnimationListener() {

        Intent intent;

        @Override
        public void onAnimationStart(Animation animation) {
            intent = new Intent(getApplicationContext(), Logo2Activity.class);
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

    private ImageView logoImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        logoImageView = findViewById(R.id.logoImageView);

        DecksContainer.init(this);

        Static.SetPortrait(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Static.Density = metrics.density;

        float animScaleStart = 1f;
        float animScaleEnd   = 1f;
        float animRelativeTo = 0.5f;

        Animation anim = new ScaleAnimation(
                animScaleStart, animScaleEnd,
                animScaleStart, animScaleEnd,
                Animation.RELATIVE_TO_SELF, animRelativeTo,
                Animation.RELATIVE_TO_SELF, animRelativeTo);
        anim.setFillAfter(true); //оставить результат, не сбрасывать на начало
        anim.setDuration(2000);
        anim.setInterpolator(new Interpolator() {

            @Override
            public float getInterpolation(float v) {
                return (float) Math.tan(v);//интерполяция по тангенсу
            }
        });
        anim.setAnimationListener(animationListener);
        logoImageView.setAnimation(anim);

    }
}
