package com.example.petever.domain.user.domain;

import com.example.petever.domain.user.enumuration.SocialType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(email, user.email) && Objects.equals(locale, user.locale) && socialType == user.socialType && Objects.equals(name, user.name) && Objects.equals(profileImage, user.profileImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, locale, socialType, name, profileImage);
    }
}
