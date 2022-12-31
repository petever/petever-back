package com.example.petever.domain.notion.domain.notion.page;

import com.example.petever.domain.notion.domain.notion.block.File;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Optional;

public class Properties {

    @JsonProperty("editor")
    private Editor editor;

    @JsonProperty("UID")
    private UID uID;

    @JsonProperty("creator")
    private Creator creator;

    @JsonProperty("sub_category")
    private SubCategory subCategory;

    @JsonProperty("edited")
    private Edited edited;

    @JsonProperty("created")
    private Created created;

    @JsonProperty("image")
    private Image image;

    @JsonProperty("main_category")
    private MainCategory mainCategory;

    @JsonProperty("middle_category")
    private MiddleCategory middleCategory;

    @JsonProperty("title")
    private Title title;

    public String getTitle() {
        return this.title.getText();
    }

    public String getCreator() {
        return this.creator.getCreator();
    }

    public Optional<String> getEditor() {
        return this.editor.getEditor();
    }

    public String getMainCategory() {
        return this.mainCategory.getCategory();
    }

    public String getMiddleCategory() {
        return this.middleCategory.getCategory();
    }

    public String getSubCategory() {
        return this.subCategory.getCategory();
    }

    public ZonedDateTime getCreated() {
        return this.created.getCreated();
    }

    public ZonedDateTime getEdited() {
        return this.edited.getEdited();
    }

    public String getImage() {
        return this.image.getImageUrl();
    }

    public void changeUrl(String savePath) {
        this.image.changeUrl(savePath);
    }

}