package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Text {

    @JsonProperty("link")
    private Object link;

    @JsonProperty("content")
    private String content;
}