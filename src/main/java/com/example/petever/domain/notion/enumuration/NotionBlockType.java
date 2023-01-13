package com.example.petever.domain.notion.enumuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum NotionBlockType {
    @JsonProperty("paragraph")
    PARAGRAPH,
    @JsonProperty("image")
    IMAGE,
    @JsonProperty("child_page")
    CHILD_PAGE,
    @JsonProperty("heading_2")
    HEADING_2,
    @JsonProperty("heading_3")
    HEADING_3,
    @JsonProperty("column_list")
    COLUMN_LIST;
}
