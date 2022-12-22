package com.example.petever.domain.notion.domain.notion.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.ZonedDateTime;

@Document(collection = "notion_page")
public class NotionPage {

    @JsonProperty("cover")
    private Object cover;

    @JsonProperty("created_time")
    private String createdTime;

    @JsonProperty("parent")
    private Parent parent;

    @JsonProperty("archived")
    private boolean archived;

    @JsonProperty("last_edited_time")
    private String lastEditedTime;

    @JsonProperty("icon")
    private Object icon;

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("created_by")
    private CreatedBy createdBy;

    @JsonProperty("last_edited_by")
    private LastEditedBy lastEditedBy;

    @JsonProperty("properties")
    private Properties properties;

    @JsonProperty("url")
    private String url;

    @JsonProperty("object")
    private String object;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return properties.getTitle();
    }

    public String getCreator() {
        return properties.getCreator();
    }

    public String getEditor() {
        return properties.getEditor().orElse(null);
    }

    public String getMainCategory() {
        return properties.getMainCategory();
    }

    public String getMiddleCategory() {
        return properties.getMiddleCategory();
    }

    public String getSubCategory() {
        return properties.getSubCategory();
    }

    public ZonedDateTime getCreated() {
        return properties.getCreated();
    }

    public ZonedDateTime getEdited() {
        return properties.getEdited();
    }

    public String getImage() {
        return properties.getImage();
    }
}