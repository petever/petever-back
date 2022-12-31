package com.example.petever.domain.notion.domain.notion.block;

import com.example.petever.domain.board.web.response.BoardBlock;
import com.example.petever.domain.notion.enumuration.NotionBlockType;
import com.example.petever.domain.notion.enumuration.NotionObjectType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Optional;

public class Results {

    private final NotionObjectType object;
    @Id
    private final String id;
    private final NotionBlockParent parent;
    private final LocalDateTime createdTime;
    private final LocalDateTime lastEditedTime;
    private final NotionBlockCreated createdBy;
    private final NotionBlockLastEdited lastEditedBy;
    private final Boolean hasChildren;
    private final Boolean archived;
    private final NotionBlockType type;
    private final ChildPage childPage;
    private final NotionImage image;
    private final Paragraph paragraph;
    private final Paragraph heading;

    @JsonCreator
    public Results(
            @JsonProperty("object") NotionObjectType object,
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
            @JsonProperty("paragraph") Paragraph paragraph,
            @JsonProperty("heading_2") Paragraph heading) {

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
        this.heading = heading;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public BoardBlock getContents() {
        String contents = "";

        if (isImage()) {
            contents = this.image.getImageContents();
        }

        if (isParagraph()) {
            contents = this.paragraph.getParagraphContents();
        }

        if (isHeader()) {
            contents = this.heading.getParagraphContents();
        }

        return new BoardBlock(this.type, contents);
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

    public Boolean isHeader() { return this.type == NotionBlockType.HEADING_2; }

    public String getId() {
        return this.id;
    }

    public String getImage() {
        return Optional.ofNullable(image).map(NotionImage::getImageContents).orElse("");
    }

    public void changeImage(String savePath) {
        Optional.ofNullable(image).ifPresent(image -> image.changeImageUrl(savePath));
    }
}
