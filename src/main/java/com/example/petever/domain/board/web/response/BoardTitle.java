package com.example.petever.domain.board.web.response;

public class BoardTitle {
    private String title;
    private String created;
    private String edited;

    public BoardTitle(String title, String created, String edited) {
        this.title = title;
        this.created = created;
        this.edited = edited;
    }
}
