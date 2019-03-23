package com.sampletext.langfocuses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.InputStream;
import java.net.URL;

public class ScheduleActivity extends AppCompatActivity {

    String imgTargetUrl = "";

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        final TextView schedule_content = findViewById(R.id.schedule_content);
        //final ProgressBar schedule_progressbar = findViewById(R.id.schedule_progressbar);
        final ImageView schedule_imageview = findViewById(R.id.schedule_image);
        final LinearLayout schedule_container = findViewById(R.id.schedule_container);

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

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.getBackground().setColorFilter(Color.parseColor("#87704A"), PorterDuff.Mode.SRC_ATOP);
        btnBack.setOnTouchListener(btnBackOnTouchListener);
        btnBack.setOnClickListener(btnBackOnClickListener);

        //final String bannerUrl = "http://itpark32.ru/banner/";
        final String bannerUrl = "http://institutnlp.tilda.ws/banerfy";


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(bannerUrl).get();
                    Element a = document.getElementsByTag("a").get(0);
                    final String ahref = a.attr("abs:href");
                    Element img = a.child(0);
                    final String imgsrc = img.attr("abs:src");
                    URL u = new URL(imgsrc);
                    InputStream inputStream = u.openStream();
                    Bitmap bitmapp = BitmapFactory.decodeStream(inputStream);
                    final Bitmap bitmap  = Bitmap.createScaledBitmap(bitmapp, 620, 620, false);
                    inputStream.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imgTargetUrl = ahref;
                            schedule_imageview.setImageBitmap(bitmap);
                            schedule_imageview.invalidate();

                            //Toast.makeText(getApplicationContext(), imgsrc + " to " + ahref, Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (final Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Loading Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //schedule_container.removeView(schedule_progressbar);
                        }
                    });
                }
            }
        }).start();


    }
}
