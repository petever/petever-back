package com.example.petever.domain.community.domain;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public class Tag {
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
