package com.sampletext.langfocuses;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sampletext.langfocuses.R;

public class PageFragment extends Fragment {

    int _pageIndex;

    Deck _deck;

    private TextView mFragmentContent;

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
        View view = inflater.inflate(R.layout.pager_fragment, null);
        mFragmentContent = view.findViewById(R.id.fragmentContent);

        mFragmentContent.setText(_deck.getCard(_pageIndex).getContent());//на данном этапе предполагается, что колода существует и содержит минимум _pageIndex+1 карт
        //_backText.setText(getResources().getString(R.string.lorem_ipsum));

        return view;
    }
}
