package com.sampletext.langfocuses;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PageFragment extends Fragment {

    int _pageIndex;

    Deck _deck;

    LinearLayout _contentContainer;

    static PageFragment newInstance(Deck deck, int page) {
        PageFragment pageFragment = new PageFragment();
        pageFragment._pageIndex = page;
        pageFragment._deck = deck;
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.pager_fragment, null);
        _contentContainer = view.findViewById(R.id.contentContainer);

        Card card = _deck.getCard(_pageIndex);
        Typeface typefaceMedium = view.getContext().getResources().getFont(R.font.roboto_medium);
        Typeface typefaceRegular = view.getContext().getResources().getFont(R.font.roboto_regular);

        for (int i = 0; i < card.getContentsLength(); i++) {
            switch (card.getContentsPart(i).get_type()) {
                case Header: {
                    TextView tv = new TextView(view.getContext());
                    tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    tv.setTypeface(typefaceMedium);
                    tv.setText(card.getContentsPart(i).get_content());
                    if (Static.DiagonalInches >= 6.5) {
                        tv.setTextSize(26 * Static.ScaleFactor);
                    }
                    else {
                        tv.setTextSize(20);
                    }
                    tv.setTextColor(_deck.getDeckColor());
                    _contentContainer.addView(tv);
                }
                break;
                case BHeader: {
                    TextView tv = new TextView(view.getContext());
                    tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    tv.setTypeface(typefaceMedium);
                    tv.setText(card.getContentsPart(i).get_content());
                    if (Static.DiagonalInches >= 6.5) {
                        tv.setTextSize(36 * Static.ScaleFactor);
                    }
                    else {
                        tv.setTextSize(30);
                    }
                    tv.setTextColor(_deck.getDeckColor());
                    _contentContainer.addView(tv);
                }
                break;
                case Plain: {
                    TextView tv = new TextView(view.getContext());
                    tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    tv.setTypeface(typefaceRegular);
                    tv.setText(card.getContentsPart(i).get_content());
                    if (Static.DiagonalInches >= 6.5) {
                        tv.setTextSize(22 * Static.ScaleFactor);
                    }
                    else {
                        tv.setTextSize(18);
                    }
                    _contentContainer.addView(tv);
                }
                break;
                case Image: {
                    ImageView iv = new ImageView(view.getContext());
                    iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    iv.setAdjustViewBounds(true);
                    byte[] bytes = Base64.decode(card.getContentsPart(i).get_content().substring(card.getContentsPart(i).get_content().indexOf(",")  + 1), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    iv.setImageBitmap(bitmap);
                    _contentContainer.addView(iv);
                }
                break;
            }

        }

        TextView tv = new TextView(view.getContext());
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tv.setTypeface(typefaceMedium);
        tv.setText(getResources().getString(R.string.aboutShortContent));

        if (Static.DiagonalInches >= 6.5) {
            tv.setTextSize(22 * Static.ScaleFactor);
        }
        else {
            tv.setTextSize(18);
        }
        _contentContainer.addView(tv);

        return view;
    }
}
