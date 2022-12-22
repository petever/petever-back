package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Creator {

    @JsonProperty("select")
    private Select select;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    public String getCreator() {
        return Optional.ofNullable(select)
                .map(Select::getName)
                .orElse("");
    }
}