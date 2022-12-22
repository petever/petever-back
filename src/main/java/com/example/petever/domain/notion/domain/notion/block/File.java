package com.example.petever.domain.notion.domain.notion.block;

import com.fasterxml.jackson.annotation.JsonProperty;

public class File {

    @JsonProperty("expiry_time")
    private String expiryTime;

    @JsonProperty("url")
    private String url;

    public String getUrl() {
        return this.url;
    }
}