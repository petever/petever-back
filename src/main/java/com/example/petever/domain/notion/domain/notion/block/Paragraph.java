package com.example.petever.domain.notion.domain.notion.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

public class Paragraph {
    private final List<RichText> richText;
    private final String color;

    @JsonCreator
    public Paragraph(@JsonProperty("rich_text") List<RichText> richText,
                     @JsonProperty("color") String color) {
        this.richText = richText;
        this.color = color;
    }

    public String getParagraphContents() {
        if (CollectionUtils.isEmpty(this.richText)) {
            return "";
        }

        return Optional.ofNullable(this.richText.get(0)).map(RichText::getTextContents).orElse("");
    }
}
