package com.example.petever.domain.user.domain;

public interface Social {

    String authorization();

    SocialUser login(String code);

    Boolean logout(String token);
}
