package com.sampletext.langfocuses;

public class ContentPart {
    public enum ContentType
    {
        Header,
        BHeader,
        Plain,
        Image
    }

    private ContentType _type;
    private String _content;

    public ContentType get_type() {
        return _type;
    }

    public String get_content() {
        return _content;
    }
    public ContentPart(ContentType type, String content)
    {
        _type = type;
        _content = content;
    }
}
