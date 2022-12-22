package com.example.petever.domain.notion.domain.notion.page;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rollup {

    @JsonProperty("array")
    private List<Object> array;

    @JsonProperty("function")
    private String function;

    @JsonProperty("type")
    private String type;
}