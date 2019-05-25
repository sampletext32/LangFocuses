package com.sampletext.langfocuses;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private Deck _deck;

    void set_deck(Deck _deck) {
        this._deck = _deck;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (position + 1) + " / " + getCount();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(_deck, position);
    }

    @Override
    public int getCount() {
        int res = 0;
        try {
            res = _deck.getCardsCount();
        } catch (NullPointerException ignored) {
        }
        return res;
    }

}