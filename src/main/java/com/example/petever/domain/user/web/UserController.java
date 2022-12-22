package com.example.petever.domain.user.web;

import com.example.petever.domain.user.application.UserService;
import com.example.petever.domain.user.application.UserSessionService;
import com.example.petever.domain.user.domain.User;
import com.example.petever.domain.user.enumuration.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserSessionService sessionService;
    private final UserService userService;

    @GetMapping("/{socialType}/authentication")
    public String authentication(@PathVariable SocialType socialType) {
        return userService.authentication(socialType);
    }

    @GetMapping("/{socialType}/login")
    public UserResponse login(@RequestParam String code, @PathVariable SocialType socialType, HttpServletRequest request) {
        User verifiedUser = userService.login(socialType, code);
        sessionService.createSession(verifiedUser, request);
        return new UserResponse(verifiedUser);
    }
}
