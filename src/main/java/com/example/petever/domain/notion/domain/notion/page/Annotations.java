package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Annotations {

    @JsonProperty("code")
    private boolean code;

    @JsonProperty("color")
    private String color;

    @JsonProperty("underline")
    private boolean underline;

    @JsonProperty("bold")
    private boolean bold;

    @JsonProperty("strikethrough")
    private boolean strikethrough;

    @JsonProperty("italic")
    private boolean italic;
}