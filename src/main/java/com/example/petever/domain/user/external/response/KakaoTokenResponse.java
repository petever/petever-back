package com.example.petever.domain.user.external.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class KakaoTokenResponse {
    private final String accessToken;
    private final String expiresIn;
    private final String refreshToken;
    private final String refreshTokenExpiresIn;
    private final String scope;
    private final String tokenType;
    private final String idToken;

    @JsonCreator
    public KakaoTokenResponse(
            @JsonProperty("access_token")
            String accessToken,
            @JsonProperty("expires_in")
            String expiresIn,
            @JsonProperty("refresh_token")
            String refreshToken,
            @JsonProperty("refresh_token_expires_in")
            String refreshTokenExpiresIn,
            @JsonProperty("scope")
            String scope,
            @JsonProperty("token_type")
            String tokenType,
            @JsonProperty("id_token")
            String idToken) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        this.scope = scope;
        this.tokenType = tokenType;
        this.idToken = idToken;
    }
}
