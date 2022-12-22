package com.example.petever.domain.notion.domain.notion.block;

import com.example.petever.domain.notion.enumuration.NotionRichTextType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RichText {
    private final NotionRichTextType type;
    private final NotionRichText text;
    private final NotionRichTextAnnotations annotations;
    private final String plainText;
    private final String href;

    @JsonCreator
    public RichText(
            @JsonProperty("type") NotionRichTextType type,
            @JsonProperty("text") NotionRichText text,
            @JsonProperty("annotations") NotionRichTextAnnotations annotations,
            @JsonProperty("plain_text") String plainText,
            @JsonProperty("href") String href) {
        this.type = type;
        this.text = text;
        this.annotations = annotations;
        this.plainText = plainText;
        this.href = href;
    }

    public String getTextContents() {
        return this.text.getContents();
    }
}
