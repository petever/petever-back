package com.example.petever.domain.board.web.response;

import com.example.petever.domain.notion.enumuration.NotionBlockType;
import lombok.Data;
import lombok.Getter;

@Getter
public class BoardBlock {
    private NotionBlockType type;
    private String contents;

    public BoardBlock(NotionBlockType type, String contents) {
        this.type = type;
        this.contents = contents;
    }
}
