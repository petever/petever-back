package com.example.petever.domain.user.external;

import com.example.petever.domain.user.domain.SocialUser;
import com.example.petever.domain.user.domain.User;
import com.example.petever.domain.user.enumuration.SocialType;
import com.example.petever.domain.user.external.request.SocialOauthRequest;
import com.example.petever.domain.user.external.response.GoogleOauthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;


@Component
public class GoogleExternalAdapter {
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private final String REDIRECT_URI;
    private final String GOOGLE_OAUTH_BASE_URL;
    private final RestTemplate restTemplate;
    private final ObjectMapper snakeCaseObjectMapper;

    @Autowired
    public GoogleExternalAdapter(@Value("${oauth.google.client.id}") String CLIENT_ID,
                                 @Value("${oauth.google.client.secret}") String CLIENT_SECRET,
                                 @Value("${oauth.google.redirect.uri}") String REDIRECT_URI,
                                 @Value("${oauth.google.api.url}") String GOOGLE_OAUTH_BASE_URL,
                                 RestTemplate restTemplate, ObjectMapper snakeCaseObjectMapper) {
        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
        this.REDIRECT_URI = REDIRECT_URI;
        this.GOOGLE_OAUTH_BASE_URL = GOOGLE_OAUTH_BASE_URL;
        this.restTemplate = restTemplate;
        this.snakeCaseObjectMapper = snakeCaseObjectMapper;
    }

    public SocialUser certification(String code) {
        try {
            GoogleOauthResponse googleOauthResponse = requestAccessToken(code);
            return verifyToken(googleOauthResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("google oauth error");
        }
    }

    private GoogleOauthResponse requestAccessToken(String code) throws JsonProcessingException {
        SocialOauthRequest socialOauthRequest = new SocialOauthRequest(code, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SocialOauthRequest> googleTokenRequestHttpEntity = new HttpEntity<>(socialOauthRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(GOOGLE_OAUTH_BASE_URL, googleTokenRequestHttpEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return snakeCaseObjectMapper.readValue(response.getBody(), GoogleOauthResponse.class);
        }

        throw new RuntimeException("?????? ?????? ??????");
    }

    private SocialUser verifyToken(GoogleOauthResponse response) throws IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singleton(CLIENT_ID))
                .build();


        GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), response.getIdToken());

        if (idToken == null) {
            throw new RuntimeException("?????? ???????????? ????????? ?????? ??? ??????");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();

        String userId = payload.getSubject();
        String email = payload.getEmail();
        String locale = (String) payload.get("locale");

        return new SocialUser(new User(userId, email, locale, SocialType.GOOGLE), response.getAccessToken());
    }

    public Boolean logout(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SocialOauthRequest> googleTokenRequestHttpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.postForEntity(String.format("https://accounts.google.com/o/oauth2/revoke?token=%s", token), googleTokenRequestHttpEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }

        throw new RuntimeException("logout error");
    }
}
