package com.example.petever.utils;

import com.example.petever.domain.user.enumuration.SocialType;

import java.beans.PropertyEditorSupport;

public class SocialTypeEnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        String capitalized = text.toUpperCase();
        SocialType socialType = SocialType.valueOf(capitalized);
        setValue(socialType);
    }
}