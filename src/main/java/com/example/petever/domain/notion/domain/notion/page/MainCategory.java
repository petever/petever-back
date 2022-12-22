package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class MainCategory {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("select")
    private Select select;

    public String getCategory() {
        return Optional.ofNullable(select).map(Select::getName).orElse(null);
    }
}