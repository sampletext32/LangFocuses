package com.sampletext.langfocuses;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;

public class DecksContainer {

    private static ArrayList<Deck> _decks = new ArrayList<>();

    public static void init(Context context) {
        _decks.clear();
        Deck loremDeck1 = Deck.getLoremDeck(context);
        Deck loremDeck2 = Deck.getLoremDeck(context);
        Deck loremDeck3 = Deck.getLoremDeck(context);
        Deck loremDeck4 = Deck.getLoremDeck(context);
        Deck loremDeck5 = Deck.getLoremDeck(context);
        loremDeck1.setHeader(loremDeck1.getHeader() + 1);
        loremDeck2.setHeader(loremDeck2.getHeader() + 2);
        loremDeck3.setHeader(loremDeck3.getHeader() + 3);
        loremDeck4.setHeader(loremDeck4.getHeader() + 4);
        loremDeck5.setHeader(loremDeck5.getHeader() + 5);
        loremDeck1.setDeckColor(Color.parseColor("#0000DD"));
        loremDeck2.setDeckColor(Color.parseColor("#FF0000"));
        loremDeck3.setDeckColor(Color.parseColor("#C8A2C8"));
        loremDeck4.setDeckColor(Color.parseColor("#008000"));
        loremDeck5.setDeckColor(Color.parseColor("#FFD700"));
        _decks.add(loremDeck1);
        _decks.add(loremDeck2);
        _decks.add(loremDeck3);
        _decks.add(loremDeck4);
        _decks.add(loremDeck5);
    }

    public static int getDecksCount() {
        return _decks.size();
    }

    public static Deck getDeck(int index) {
        if (index < _decks.size()) {
            return _decks.get(index);
        }
        else {
            return null;
        }
    }
}
