package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parent {

    @JsonProperty("database_id")
    private String databaseId;

    @JsonProperty("type")
    private String type;
}