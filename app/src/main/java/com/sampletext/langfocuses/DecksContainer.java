package com.sampletext.langfocuses;

import android.content.Context;
import android.graphics.Color;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DecksContainer {

    private static ArrayList<Deck> _decks = new ArrayList<>();

    public static void init(Context context) {
        _decks.clear();
        try {
            InputStream stream = context.getResources().openRawResource(R.raw.deck_data);
            byte[] buffer = new byte[stream.available()];
            int read = stream.read(buffer);
            stream.close();
            if (read == buffer.length) {
                String deckDataJsonString = new String(buffer, StandardCharsets.UTF_8);
                JSONObject jsonObject = new JSONObject(deckDataJsonString);
                JSONArray jsonDecks = jsonObject.getJSONArray("decks");
                for (int i = 0; i < jsonDecks.length(); i++) {
                    try {
                        JSONObject jsonDeck = jsonDecks.getJSONObject(i);
                        String name = jsonDeck.getString("name");
                        String colorString = jsonDeck.getString("color");
                        int deckColor = Color.parseColor(colorString);
                        Deck deck = new Deck(name, deckColor);
                        JSONArray jsonCards = jsonDeck.getJSONArray("cards");
                        for (int j = 0; j < jsonCards.length(); j++) {
                            try {
                                JSONObject jsonCard = jsonCards.getJSONObject(j);
                                JSONArray jsonCardContents = jsonCard.getJSONArray("contents");
                                Card card = new Card();
                                for (int k = 0; k < jsonCardContents.length(); k++) {
                                    try {
                                        JSONObject jsonCardContentItem = jsonCardContents.getJSONObject(k);
                                        String type = jsonCardContentItem.getString("type");
                                        String content = jsonCardContentItem.getString("content");
                                        ContentPart.ContentType contentType;
                                        switch (type) {
                                            case "header":
                                                contentType = ContentPart.ContentType.Header;
                                                break;
                                            case "bheader":
                                                contentType = ContentPart.ContentType.BHeader;
                                                break;
                                            case "image":
                                                contentType = ContentPart.ContentType.Image;
                                                break;
                                            case "plain":
                                            default:
                                                contentType = ContentPart.ContentType.Plain;
                                                break;
                                        }

                                        card.appendContents(new ContentPart(contentType, content));
                                    } catch (Exception ignored) {

                                    }
                                }
                                deck.addCard(card);
                            } catch (Exception ignored) {

                            }
                        }
                        _decks.add(deck);
                    } catch (Exception ignored) {

                    }
                }
            }
        } catch (Exception ignored) {

        }
    }

    public static int getDecksCount() {
        return _decks.size();
    }

    public static Deck getDeck(int index) {
        if (index < _decks.size()) {
            return _decks.get(index);
        } else {
            return null;
        }
    }
}
