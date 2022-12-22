package com.example.petever.domain.notion.domain.notion.page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Title {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("title")
    private List<TitleItem> title;

    // FIXME: 테스트코드 작성 필요
    public String getText() {
        return Optional.ofNullable(title)
                .orElseThrow(() -> new RuntimeException("Not Found Title Text"))
                .stream()
                .map(TitleItem::getPlainText)
                .collect(Collectors.joining());
    }
}