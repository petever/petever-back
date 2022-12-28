package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Image {

    @JsonProperty("files")
    private List<FilesItem> files;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    public List<FilesItem> getFiles() {
        return files;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getImageUrl() {
        return Optional.ofNullable(files)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(FilesItem::getFile)
                .map(File::getUrl)
                .findFirst()
                .orElse(null);
    }
}