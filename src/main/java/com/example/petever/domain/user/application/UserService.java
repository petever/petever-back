package com.example.petever.domain.user.application;

import com.example.petever.domain.user.domain.Social;
import com.example.petever.domain.user.domain.SocialUser;
import com.example.petever.domain.user.enumuration.SocialType;
import com.example.petever.domain.user.factory.SocialFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SocialFactory socialFactory;

    public String authentication(SocialType socialType) {
        Social social = socialFactory.create(socialType);
        return social.authorization();
    }

    public SocialUser login(SocialType socialType, String code) {
        Social social = socialFactory.create(socialType);
        return social.login(code);
    }

    public Boolean logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SocialUser user = (SocialUser) session.getAttribute("user");
        System.out.println("user = " + user);
        System.out.println("user.getSocialType() = " + user.getSocialType());
        Social social = socialFactory.create(user.getSocialType());
        return social.logout(user.getToken());
    }
}
