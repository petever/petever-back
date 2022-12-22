package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class Editor {

    @JsonProperty("select")
    private Select select;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    public Optional<String> getEditor() {
        return Optional.ofNullable(select).map(Select::getName);
    }
}