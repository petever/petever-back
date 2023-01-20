package com.example.petever.domain.user.application;

import com.example.petever.domain.user.domain.Social;
import com.example.petever.domain.user.domain.SocialUser;
import com.example.petever.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSessionService {
    public Optional<User> checkSession(HttpSession userSession) {
        return (Optional<User>) Optional.ofNullable(userSession)
                .map(session -> session.getAttribute("user"))
                .orElse(null);
    }

    public void createSession(SocialUser user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }

    public void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
