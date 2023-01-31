package com.example.petever.domain.user.domain;

import com.example.petever.domain.user.external.GoogleExternalAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Google implements Social {

    private final GoogleExternalAdapter googleExternalAdapter;
    private final String GOOGLE_AUTHENTICATION_URL;

    @Autowired
    public Google(GoogleExternalAdapter googleExternalAdapter,
                  @Value("${oauth.google.authentication.url}") String GOOGLE_AUTHENTICATION_URL) {
        this.googleExternalAdapter = googleExternalAdapter;
        this.GOOGLE_AUTHENTICATION_URL = GOOGLE_AUTHENTICATION_URL;
    }

    @Override
    public String authorization() {
        return GOOGLE_AUTHENTICATION_URL;
    }

    @Override
    public User login(String code) {
        return googleExternalAdapter.certification(code);
    }

    @Override
    public void logout(String key) {

    }


}
