package com.example.petever.domain.notion.domain.notion.block;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Bookmark {
    private final ArrayList<String> caption;
    private final String url;

    public Bookmark(
            @JsonProperty("caption") ArrayList<String> caption,
            @JsonProperty("url") String url) {
        this.caption = caption;
        this.url = url;
    }

    public String getUrl() {
        if (url == null) {
            return "";
        }
        return this.getUrl();
    }
}
