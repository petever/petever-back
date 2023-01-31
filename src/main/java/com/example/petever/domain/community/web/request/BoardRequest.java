package com.example.petever.domain.community.web.request;

import com.example.petever.domain.community.domain.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BoardRequest {
    private final String title;
    private final String contents;
    private final List<Tag> tags;
}
