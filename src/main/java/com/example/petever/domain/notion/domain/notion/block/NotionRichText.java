package com.example.petever.domain.notion.domain.notion.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotionRichText {
    private final String content;
    private final Object link;

    @JsonCreator
    public NotionRichText(
            @JsonProperty("content") String content,
            @JsonProperty("link") Object link) {
        this.content = content;
        this.link = link;
    }

    public String getContents() {
        return content;
    }
}
