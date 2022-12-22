package com.example.petever.domain.user.factory;

import com.example.petever.domain.user.domain.Google;
import com.example.petever.domain.user.domain.Kakao;
import com.example.petever.domain.user.domain.Social;
import com.example.petever.domain.user.enumuration.SocialType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SocialFactory {
    private final Map<SocialType, Social> immutableMap;

    @Autowired
    public SocialFactory(Google google, Kakao kakao) {
        this.immutableMap = Map.of(
                SocialType.GOOGLE, google,
                SocialType.KAKAO, kakao
        );
    }

    public Social create(SocialType socialType) {
        return immutableMap.get(socialType);
    }
}
