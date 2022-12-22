package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TitleItem {

    @JsonProperty("plain_text")
    private String plainText;

    @JsonProperty("annotations")
    private Annotations annotations;

    @JsonProperty("text")
    private Text text;

    @JsonProperty("href")
    private Object href;

    @JsonProperty("type")
    private String type;

    public String getPlainText() {
        return plainText;
    }
}