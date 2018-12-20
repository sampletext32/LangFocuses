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
    public static Deck getLoremDeck(Context context) {
        Deck deck  = new Deck("Lorem", Color.BLACK);
        Card card1 = new Card(generateContent(1));
        Card card2 = new Card(generateContent(2));
        Card card3 = new Card(generateContent(3));
        Card card4 = new Card(generateContent(4));
        Card card5 = new Card(generateContent(5));
        Card card6 = new Card(generateContent(6));
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);
        deck.addCard(card5);
        deck.addCard(card6);
        return deck;
    }
    private static String generateContent(int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append("I'm card " + index + "\n");
        }
        return builder.toString();
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
