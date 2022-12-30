package com.example.petever.domain.board.web.response;

import lombok.Getter;

import java.util.List;

@Getter
public class BoardBlocks {
    private final BoardTitle title;
    private final List<BoardBlock> boardBlocks;

    public BoardBlocks(BoardTitle title, List<BoardBlock> boardBlocks) {
        this.title = title;
        this.boardBlocks = boardBlocks;
    }
}
