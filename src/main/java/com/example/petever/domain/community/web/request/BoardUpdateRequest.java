package com.example.petever.domain.community.web.request;

import com.example.petever.domain.community.domain.Tag;
import com.example.petever.domain.community.enumuration.BoardType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BoardUpdateRequest {
    private final String title;
    private final String contents;
    private final String authorId;
    private final List<Tag> tags;
}
