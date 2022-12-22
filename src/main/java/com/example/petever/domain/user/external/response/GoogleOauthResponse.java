package com.example.petever.domain.user.external.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GoogleOauthResponse {

    private final String accessToken;
    private final String expiresIn;
    private final String refreshToken;
    private final String scope;
    private final String tokenType;
    @Getter
    private final String idToken;

}
