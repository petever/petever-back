package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class File {

    @JsonProperty("expiry_time")
    private String expiryTime;

    @JsonProperty("url")
    private String url;

    public String getExpiryTime() {
        return expiryTime;
    }

    public String getUrl() {
        return url;
    }
}