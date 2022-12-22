package com.example.petever.domain.user.external.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KakaoTokenResponse {
    private final String accessToken;
    private final String expiresIn;
    private final String refreshToken;
    private final String refreshTokenExpiresIn;
    private final String scope;
    private final String tokenType;
    private final String idToken;
}
