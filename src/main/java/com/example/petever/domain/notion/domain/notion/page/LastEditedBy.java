package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LastEditedBy {

    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;
}