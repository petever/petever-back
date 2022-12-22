package com.example.petever.domain.notion.domain.notion.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChildPage {

    private final String title;

    @JsonCreator
    public ChildPage(@JsonProperty("title") String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
