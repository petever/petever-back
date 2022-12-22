package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class Created {

    @JsonProperty("created_time")
    private String createdTime;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    public ZonedDateTime getCreated() {
        return ZonedDateTime.parse(this.createdTime);
    }
}