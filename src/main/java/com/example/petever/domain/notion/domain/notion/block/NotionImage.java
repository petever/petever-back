package com.example.petever.domain.notion.domain.notion.block;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

public class NotionImage {

    @JsonProperty("file")
    private File file;

    @JsonProperty("caption")
    private List<Object> caption;

    @JsonProperty("type")
    private String type;

    public String getImageContents() {
        return Optional.ofNullable(file).map(File::getUrl).orElse("");
    }
}