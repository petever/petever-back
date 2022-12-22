package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Formula {

    @JsonProperty("string")
    private String string;

    @JsonProperty("type")
    private String type;
}