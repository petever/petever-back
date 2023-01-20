package com.example.petever.domain.user.domain;

import com.example.petever.domain.user.enumuration.SocialType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

@Getter
@RequiredArgsConstructor
public class SocialUser {
    private final User user;
    private final String token;

    public SocialType getSocialType() {
        return user.getSocialType();
    }
}
