package com.example.petever.domain.community.web.request;

import com.example.petever.domain.community.domain.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardRequest {
    private String title;
    private String contents;
    private List<Tag> tags;
}
