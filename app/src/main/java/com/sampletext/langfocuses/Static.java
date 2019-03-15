package com.sampletext.langfocuses;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.Calendar;
import java.util.Random;

public class Static {

    public static Random rnd = new Random(Calendar.getInstance().get(Calendar.MILLISECOND));

    static float DiagonalInches = 0f;

    static float Density = 0f;

    static float ScaleFactor = 0f;

    static void SetPortrait(Activity a) {
        if (a == null) {
            return;
        }
        a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    static void SetViewScale(View root) {
        if (root == null) {
            return;
        }
        //This Weird thing is needed to wait for app to calculate sizes
        root.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            View view;

            @Override
            public boolean onPreDraw() {
                if (view.getViewTreeObserver().isAlive())
                    view.getViewTreeObserver().removeOnPreDrawListener(this);

                //Делаем рассчёт относительно соотношения 16/9.
                if (Math.abs(view.getHeight() / (float) view.getWidth() - 16 / 9f) > 0.01f) {
                    float factor = (view.getWidth() * 16 / 9f) / view.getHeight();
                    factor = factor + (1 - factor) / 2;
                    view.setScaleY(factor);
                }


                return false;
            }

            ViewTreeObserver.OnPreDrawListener setView(View v) {
                view = v;
                return this;
            }
        }.setView(root));
    }
}
