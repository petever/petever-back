package com.example.petever.domain.notion.domain.notion.block;

import com.example.petever.domain.notion.enumuration.NotionObjectType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotionBlockCreated {
    private final NotionObjectType object;
    private final String id;

    @JsonCreator
    public NotionBlockCreated(
            @JsonProperty("object") NotionObjectType object,
            @JsonProperty("id") String id) {
        this.object = object;
        this.id = id;
    }
}
