package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class Edited {

    @JsonProperty("last_edited_time")
    private String lastEditedTime;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    public ZonedDateTime getEdited() {
        return ZonedDateTime.parse(lastEditedTime);
    }
}