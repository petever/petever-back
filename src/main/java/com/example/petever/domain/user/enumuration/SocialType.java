package com.example.petever.domain.user.enumuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SocialType {
    @JsonProperty("google")
    GOOGLE,

    @JsonProperty("update")
    KAKAO
}
