package com.example.petever.domain.user.external;

import com.example.petever.domain.user.domain.SocialUser;
import com.example.petever.domain.user.domain.User;
import com.example.petever.domain.user.enumuration.SocialType;
import com.example.petever.domain.user.external.request.SocialOauthRequest;
import com.example.petever.domain.user.external.response.KakaoTokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Component
public class KakaoExternalAdapter {
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private final String REDIRECT_URI;
    private final String KAKAO_OAUTH_BASE_URL;
    private final RestTemplate restTemplate;
    private final ObjectMapper snakeCaseObjectMapper;

    @Autowired
    public KakaoExternalAdapter(
            @Value("${oauth.kakao.client.id}") String CLIENT_ID,
            @Value("${oauth.kakao.client.secret}") String CLIENT_SECRET,
            @Value("${oauth.kakao.redirect.uri}") String REDIRECT_URI,
            @Value("${oauth.kakao.api.url}") String KAKAO_OAUTH_BASE_URL,
            RestTemplate restTemplate,
            ObjectMapper snakeCaseObjectMapper) {
        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
        this.REDIRECT_URI = REDIRECT_URI;
        this.KAKAO_OAUTH_BASE_URL = KAKAO_OAUTH_BASE_URL;
        this.restTemplate = restTemplate;
        this.snakeCaseObjectMapper = snakeCaseObjectMapper;
    }

    public SocialUser certification(String code) {
        try {
            KakaoTokenResponse response = requestAccessToken(code);
            return verifyToken(response);
        } catch (Exception e) {
            throw new RuntimeException("kakao oauth error");
        }
    }

    private KakaoTokenResponse requestAccessToken(String code) throws JsonProcessingException {
        SocialOauthRequest socialOauthRequest = new SocialOauthRequest(code, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
        MultiValueMap param = new LinkedMultiValueMap();
        param.add("code", socialOauthRequest.getCode());
        param.add("client_id", socialOauthRequest.getClientId());
        param.add("client_secret", socialOauthRequest.getClientSecret());
        param.add("grant_type", socialOauthRequest.getGrantType());
        param.add("redirect_uri", socialOauthRequest.getRedirectUri());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap> tokenRequestHttpEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(KAKAO_OAUTH_BASE_URL, tokenRequestHttpEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return snakeCaseObjectMapper.readValue(response.getBody(), KakaoTokenResponse.class);
        }

        throw new RuntimeException("카카오 인증 에러");
    }

    private SocialUser verifyToken(KakaoTokenResponse kakaoTokenResponse) throws IOException {
        String idToken = kakaoTokenResponse.getIdToken();
        String[] chunks = idToken.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        Map map = snakeCaseObjectMapper.readValue(payload, Map.class);
        String userId = (String) map.get("sub");
        String email = (String) map.get("email");

        return new SocialUser(new User(userId, email, "", SocialType.KAKAO), kakaoTokenResponse.getAccessToken());
    }

    public Boolean logout(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<MultiValueMap> tokenRequestHttpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout", tokenRequestHttpEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }

        throw new RuntimeException("logout error");
    }
}
