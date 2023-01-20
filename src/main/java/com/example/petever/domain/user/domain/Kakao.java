package com.example.petever.domain.user.domain;

import com.example.petever.domain.user.external.KakaoExternalAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Kakao implements Social {

    private final KakaoExternalAdapter kakaoExternalAdapter;
    private final String KAKAO_ACCOUNT_URL;

    public Kakao(KakaoExternalAdapter kakaoExternalAdapter, @Value("${oauth.kakao.authentication.url}") String KAKAO_ACCOUNT_URL) {
        this.kakaoExternalAdapter = kakaoExternalAdapter;
        this.KAKAO_ACCOUNT_URL = KAKAO_ACCOUNT_URL;
    }

    @Override
    public String authorization() {
        return KAKAO_ACCOUNT_URL;
    }

    @Override
    public SocialUser login(String code) {
        return kakaoExternalAdapter.certification(code);
    }

    @Override
    public Boolean logout(String token) {
        return kakaoExternalAdapter.logout(token);
    }
}
