package com.sampletext.langfocuses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends Activity {

    String imgTargetUrl = "";

    View.OnTouchListener btn_Highlight_OnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_HOVER_ENTER:
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setColorFilter(Color.parseColor("#F5BBBBBB"), PorterDuff.Mode.SRC_ATOP);
                    break;
                case MotionEvent.ACTION_HOVER_EXIT:
                case MotionEvent.ACTION_UP:
                    v.getBackground().clearColorFilter();
                    break;

                default:
            }
            return false;
        }
    };

    private View.OnClickListener BtnHowToUse_OnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), HowToUseActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener BtnChooseDeck_OnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), SelectDeckActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener BtnAbout_OnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_main);
        } catch (OutOfMemoryError e) {
            Toast.makeText(getApplicationContext(), "Not enough memory", Toast.LENGTH_SHORT).show();
            finish();
        }

        Static.SetPortrait(this);

        Static.SetViewScale(findViewById(R.id.main_root));

        TextView header_antifocusy = findViewById(R.id.main_header_antifocusy);
        TextView header_yazyka = findViewById(R.id.main_header_yazyka);


        //Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.fk_mandarin);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/_fk_mandarin.ttf");
        header_antifocusy.setTypeface(typeface);
        header_yazyka.setTypeface(typeface);


        Button _btnChooseDeck = findViewById(R.id.btn_choose_deck);
        Button _btnAbout = findViewById(R.id.btn_about);
        Button _btnHowToUse = findViewById(R.id.btn_how_to_use);

        Static.fitText(header_antifocusy);
        Static.fitText(header_yazyka);
        Static.fitText(_btnAbout);
        Static.fitText(_btnChooseDeck);
        Static.fitText(_btnHowToUse);

        _btnHowToUse.setOnTouchListener(btn_Highlight_OnTouchListener);
        _btnChooseDeck.setOnTouchListener(btn_Highlight_OnTouchListener);
        _btnAbout.setOnTouchListener(btn_Highlight_OnTouchListener);

        _btnHowToUse.setOnClickListener(BtnHowToUse_OnClickListener);
        _btnChooseDeck.setOnClickListener(BtnChooseDeck_OnClickListener);
        _btnAbout.setOnClickListener(BtnAbout_OnClickListener);


        //BANNER LOADING ROUTINE

        final TextView schedule_content = findViewById(R.id.schedule_content);
        final ProgressBar schedule_progressbar = findViewById(R.id.schedule_progressbar);
        final ImageView schedule_imageview = findViewById(R.id.schedule_image);
        final FrameLayout schedule_container = findViewById(R.id.schedule_container);

        schedule_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!imgTargetUrl.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imgTargetUrl));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Image Is Not Loaded", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final String bannerUrl = "http://institutnlp.tilda.ws/banerfy";

        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = (activeNetwork != null) && activeNetwork.isConnected();

        if (isConnected) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Document document = Jsoup.connect(bannerUrl).timeout(5000).get();
                        Element a = document.getElementsByTag("a").get(0);
                        final String ahref = a.attr("abs:href");
                        Element img = a.child(0);
                        final String imgsrc = img.attr("abs:data-original");
                        URL u = new URL(imgsrc);
                        InputStream inputStream = u.openStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imgTargetUrl = ahref;
                                schedule_imageview.setImageBitmap(bitmap);
                                schedule_imageview.invalidate();
                            }
                        });

                    }
                    catch (final Exception e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                schedule_container.removeView(schedule_imageview);
                                schedule_content.setText("Не удалось установить соединение с интернетом");
                            }
                        });
                    }
                    finally {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                schedule_container.removeView(schedule_progressbar);
                            }
                        });
                    }
                }
            }).start();
        } else {
            //schedule_container.removeView(schedule_imageview);
            ColorDrawable colorDrawable = new ColorDrawable();
            colorDrawable.setColor(Color.parseColor("#55E6C287"));
            schedule_imageview.setBackground(colorDrawable);
            schedule_content.setText("Не удалось установить соединение с интернетом");
            //schedule_container.removeView(schedule_btn_more);
        }
        schedule_container.removeView(schedule_progressbar);
    }
}

