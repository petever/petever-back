package com.example.petever.domain.user.domain;

import com.example.petever.domain.user.enumuration.SocialType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {

    private final String userId;
    @Getter
    private final String email;
    private final String locale;
    @Getter
    private final SocialType socialType;
    @Getter
    private String name;
    private String profileImage;
}
