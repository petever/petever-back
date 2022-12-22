package com.example.petever.domain.user.application;

import com.example.petever.domain.user.domain.Social;
import com.example.petever.domain.user.domain.User;
import com.example.petever.domain.user.enumuration.SocialType;
import com.example.petever.domain.user.factory.SocialFactory;
import com.example.petever.domain.user.web.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SocialFactory socialFactory;

    public String authentication(SocialType socialType) {
        Social social = socialFactory.create(socialType);
        return social.authorization();
    }

    public User login(SocialType socialType, String code) {
        Social social = socialFactory.create(socialType);
        return social.login(code);
    }
}
