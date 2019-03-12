package com.sampletext.langfocuses;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;

public class Deck {

    private String _header;

    private ArrayList<Card> _cards;

    private int _deckColor;

    public Deck(String header, int deckColor) {
        _header = header;
        _deckColor = deckColor;
        _cards = new ArrayList<>();
    }
    public String getHeader() {
        return _header;
    }
    public void setHeader(String header) {
        _header = header;
    }
    public void addCard(Card card) {
        _cards.add(card);
    }
    public Card getCard(int index) {
        if (index < _cards.size()) {
            return _cards.get(index);
        }
        else {
            return null;
        }
    }
    public int getCardsCount() {
        return _cards.size();
    }
    public int getDeckColor() {
        return _deckColor;
    }
    public void setDeckColor(int _deckColor) {
        this._deckColor = _deckColor;
    }
}
