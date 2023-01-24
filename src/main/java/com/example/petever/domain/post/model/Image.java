package com.example.petever.domain.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Map;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Image {
    private Map<String, String> props;
    private String thumbnailImagePath;
    private String originImagePath;
}
