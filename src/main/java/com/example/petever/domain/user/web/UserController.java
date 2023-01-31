package com.example.petever.domain.user.web;

import com.example.petever.domain.user.application.UserService;
import com.example.petever.domain.user.application.UserSessionService;
import com.example.petever.domain.user.domain.User;
import com.example.petever.domain.user.enumuration.SocialType;
import com.example.petever.utils.SocialTypeEnumConverter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserSessionService sessionService;
    private final UserService userService;

    @GetMapping("/{socialType}/authentication")
    public void authentication(@PathVariable SocialType socialType, HttpServletResponse response) throws IOException {
        response.sendRedirect(userService.authentication(socialType));
    }

    @GetMapping("/{socialType}/login")
    public void login(@PathVariable SocialType socialType, @RequestParam String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User verifiedUser = userService.login(socialType, code);
        sessionService.createSession(verifiedUser, request);
        response.sendRedirect("https://petever.pet");
    }

    @PostMapping("/{socialType}/logout")
    public void logout(@PathVariable SocialType socialType) throws IOException {
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SocialType.class, new SocialTypeEnumConverter());
    }
}
