package com.example.petever.domain.notion.domain.notion.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotionRichTextAnnotations {
    private final Boolean bold;
    private final Boolean italic;
    private final Boolean strikethrough;
    private final Boolean underline;
    private final Boolean code;
    private final String color;

    @JsonCreator
    public NotionRichTextAnnotations(
            @JsonProperty("bold") Boolean bold,
            @JsonProperty("italic") Boolean italic,
            @JsonProperty("strikethrough") Boolean strikethrough,
            @JsonProperty("underline") Boolean underline,
            @JsonProperty("code") Boolean code,
            @JsonProperty("color") String color
    ) {
        this.bold = bold;
        this.italic = italic;
        this.strikethrough = strikethrough;
        this.underline = underline;
        this.code = code;
        this.color = color;
    }
}
