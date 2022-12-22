package com.example.petever.domain.board.web.response;

import com.example.petever.domain.notion.domain.notion.page.NotionPage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class BoardSummary {

    private final String uid;
    private final String title;
    private final String creator;
    private final String editor;
    private final String mainCategory;
    private final String middleCategory;
    private final String subCategory;
    private final String image;
    private final ZonedDateTime created;
    private final ZonedDateTime edited;

    public BoardSummary(NotionPage notionPage) {
        this.uid = notionPage.getId();
        this.title = notionPage.getTitle();
        this.creator = notionPage.getCreator();
        this.editor = notionPage.getEditor();
        this.mainCategory = notionPage.getMainCategory();
        this.middleCategory = notionPage.getMiddleCategory();
        this.subCategory = notionPage.getSubCategory();
        this.created = notionPage.getCreated();
        this.edited = notionPage.getEdited();
        this.image = notionPage.getImage();
    }

}
