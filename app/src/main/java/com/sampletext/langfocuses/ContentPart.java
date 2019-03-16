package com.sampletext.langfocuses;

class ContentPart {
    public enum ContentType
    {
        Header,
        BHeader,
        Plain,
        Image
    }

    private ContentType _type;
    private String _content;

    ContentType get_type() {
        return _type;
    }

    String get_content() {
        return _content;
    }
    ContentPart(ContentType type, String content)
    {
        _type = type;
        _content = content;
    }
}
