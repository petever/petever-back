package com.example.petever.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {

    private final String userId;
    @Getter
    private final String email;
    private final String locale;
    @Getter
    private String name;
}
