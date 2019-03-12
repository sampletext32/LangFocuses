package com.sampletext.langfocuses;

import java.util.ArrayList;

public class Card {

    private ArrayList<ContentPart> _contents;

    public Card() {
        _contents = new ArrayList<>();
    }
    public void appendContents(ContentPart contentPart)
    {
        _contents.add(contentPart);
    }
    public int getContentsLength()
    {
        return _contents.size();
    }
    public ContentPart getContentsPart(int index)
    {
        return _contents.get(index);
    }
}