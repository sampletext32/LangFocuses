package com.sampletext.langfocuses;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private Deck _deck;

    public void set_deck(Deck _deck) {
        this._deck = _deck;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return (position + 1) + " / " + getCount();
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(_deck, position);
    }

    @Override
    public int getCount() {
        return _deck.getCardsCount();
    }

}