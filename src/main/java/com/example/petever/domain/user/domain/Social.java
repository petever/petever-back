package com.example.petever.domain.user.domain;

public interface Social {

    String authorization();

    User login(String code);

    void logout(String key);
}
