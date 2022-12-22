package com.example.petever.domain.notion.domain.notion.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class NotionBlockParent {
    private final String type;
    private final String pageId;

    @JsonCreator
    public NotionBlockParent(
            @JsonProperty("type") String type,
            @JsonProperty("page_id") String pageId) {
        this.type = type;
        this.pageId = pageId;
    }
}
