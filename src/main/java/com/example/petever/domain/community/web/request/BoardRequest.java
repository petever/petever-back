package com.example.petever.domain.community.web.request;

import com.example.petever.domain.community.domain.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BoardRequest {
    private final String title;
    private final String contents;
    private final List<Tag> tags;
}
