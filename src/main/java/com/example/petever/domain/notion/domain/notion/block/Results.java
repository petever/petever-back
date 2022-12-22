package com.example.petever.domain.notion.domain.notion.block;

import com.example.petever.domain.notion.enumuration.NotionBlockType;
import com.example.petever.domain.notion.enumuration.NotionObjectType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import java.time.LocalDate;

public class Results {

    private final NotionObjectType object;
    @Id
    private final String id;
    private final NotionBlockParent parent;
    private final LocalDate createdTime;
    private final LocalDate lastEditedTime;
    private final NotionBlockCreated createdBy;
    private final NotionBlockLastEdited lastEditedBy;
    private final Boolean hasChildren;
    private final Boolean archived;
    private final NotionBlockType type;
    private final ChildPage childPage;
    private final NotionImage image;
    private final Paragraph paragraph;

    @JsonCreator
    public Results(
            @JsonProperty("object") NotionObjectType object,
            @JsonProperty("id") String id,
            @JsonProperty("parent") NotionBlockParent parent,
            @JsonProperty("created_time") LocalDate createdTime,
            @JsonProperty("last_edited_time") LocalDate lastEditedTime,
            @JsonProperty("create_by") NotionBlockCreated createdBy,
            @JsonProperty("last_edited_by") NotionBlockLastEdited lastEditedBy,
            @JsonProperty("has_children") Boolean hasChildren,
            @JsonProperty("archived") Boolean archived,
            @JsonProperty("type") NotionBlockType type,
            @JsonProperty("image") NotionImage image,
            @JsonProperty("child_page") ChildPage childPage,
            @JsonProperty("paragraph") Paragraph paragraph) {

        this.object = object;
        this.id = id;
        this.parent = parent;
        this.createdTime = createdTime;
        this.lastEditedTime = lastEditedTime;
        this.createdBy = createdBy;
        this.lastEditedBy = lastEditedBy;
        this.hasChildren = hasChildren;
        this.archived = archived;
        this.type = type;
        this.image = image;
        this.childPage = childPage;
        this.paragraph = paragraph;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public String getContents() {
        if (isImage()) {
            return this.image.getImageContents();
        }

        if (isParagraph()) {
            return this.paragraph.getParagraphContents();
        }

        return "";
    }

    public String getTitle() {
        return isChildPage() ? childPage.getTitle() : "";
    }

    public Boolean isImage() {
        return this.type == NotionBlockType.IMAGE;
    }

    public Boolean isParagraph() {
        return this.type == NotionBlockType.PARAGRAPH;
    }

    public Boolean isChildPage() {
        return this.type == NotionBlockType.CHILD_PAGE;
    }

    public String getId() {
        return this.id;
    }
}
