package com.example.petever.domain.notion.domain.notion.block;

import com.example.petever.domain.board.web.response.BoardBlock;
import com.example.petever.domain.notion.enumuration.NotionObjectType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "notion_blocks")
public class NotionBlocks {

    @Id
    private final String id;
    private final NotionObjectType object;
    private final List<NotionBlock> results;

    @JsonCreator
    public NotionBlocks(
            @JsonProperty("id") String id, @JsonProperty("object") NotionObjectType object,
            @JsonProperty("results") List<NotionBlock> results
    ) {
        this.id = id;
        this.object = object;
        this.results = results;
    }

    public List<BoardBlock> getContentBlock() {
        return results.stream()
                .map(Results::getContents)
                .collect(Collectors.toList());
    }

    public List<String> getContentBlockAndLineSeparator() {
        return results.stream()
                .map(results -> results.getContents() + System.getProperty("line.separator"))
                .collect(Collectors.toList());
    }

    public List<String> getPageIds() {
        return results.stream()
                .map(Results::getId)
                .collect(Collectors.toList());
    }

    public String getImage() {
        return results.stream()
                .map(Results::getImage)
                .collect(Collectors.joining());
    }

    public String getId() {
        return this.id;
    }

    public void changeImage(String savePath) {
        results.stream()
                .forEach(result -> result.changeImage(savePath));
    }

    public List<NotionBlock> getResults() {
        return results;
    }
}
