package com.example.petever.domain.notion.enumuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum NotionObjectType {
    @JsonProperty("list")
    LIST,
    @JsonProperty("block")
    BLOCK,
    @JsonProperty("page")
    PAGE,
    @JsonProperty("user")
    USER

}
