package com.example.petever.domain.user.web;

import com.example.petever.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserResponse {

    private final User user;

    @Autowired
    public UserResponse(User user) {
        this.user = user;
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public String getName() {
        return this.user.getName();
    }
}
