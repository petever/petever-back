package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilesItem {

    @JsonProperty("file")
    private File file;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}