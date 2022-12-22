package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UID {

    @JsonProperty("formula")
    private Formula formula;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;
}