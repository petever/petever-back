package com.example.petever.domain.notion.domain.notion.block;

import com.example.petever.domain.notion.enumuration.NotionBlockType;
import com.example.petever.domain.notion.enumuration.NotionObjectType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "notion_block")
public class NotionBlock extends Results {

    public NotionBlock(@JsonProperty("object") NotionObjectType object,
                       @JsonProperty("id") String id,
                       @JsonProperty("parent") NotionBlockParent parent,
                       @JsonProperty("created_time") LocalDateTime createdTime,
                       @JsonProperty("last_edited_time") LocalDateTime lastEditedTime,
                       @JsonProperty("create_by") NotionBlockCreated createdBy,
                       @JsonProperty("last_edited_by") NotionBlockLastEdited lastEditedBy,
                       @JsonProperty("has_children") Boolean hasChildren,
                       @JsonProperty("archived") Boolean archived,
                       @JsonProperty("type") NotionBlockType type,
                       @JsonProperty("image") NotionImage image,
                       @JsonProperty("child_page") ChildPage childPage,
                       @JsonProperty("paragraph") Paragraph paragraph) {
        super(object, id, parent, createdTime, lastEditedTime, createdBy, lastEditedBy, hasChildren, archived, type, image, childPage, paragraph);
    }

    public String getTitle() {
        return super.getTitle();
    }

    public String getId() {
        return super.getId();
    }

}
