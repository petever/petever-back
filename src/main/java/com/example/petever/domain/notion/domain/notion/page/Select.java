package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Select {

    @JsonProperty("color")
    private String color;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    public String getName() {
        return this.name;
    }
}