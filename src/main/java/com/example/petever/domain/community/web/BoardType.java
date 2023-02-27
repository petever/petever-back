package com.example.petever.domain.community.web;

public enum BoardType {
    QUESTIONS("questions"),
    FREE("free");

    private String code;

    BoardType(String code) {
        this.code = code;
    }
}
