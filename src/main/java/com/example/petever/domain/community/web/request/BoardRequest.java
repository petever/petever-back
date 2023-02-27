package com.example.petever.domain.community.web.request;

import com.example.petever.domain.community.domain.Tag;
import com.example.petever.domain.community.web.BoardType;
import com.example.petever.domain.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BoardRequest {
    private final String title;
    private final String contents;
    private final List<Tag> tags;
    private final BoardType boardType;
}
