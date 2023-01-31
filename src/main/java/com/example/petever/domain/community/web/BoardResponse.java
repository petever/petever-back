package com.example.petever.domain.community.web;

import com.example.petever.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {

    private String title;
    private String contents;
    private String preview;
    // Author 객체
    private String author;
    private Integer commentCount;
    private Integer viewCount;

    // Tag 객체
}
