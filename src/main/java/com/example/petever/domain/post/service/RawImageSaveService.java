package com.example.petever.domain.post.service;

import com.example.petever.domain.post.model.Image;
import com.example.petever.domain.post.model.RawImage;

public interface RawImageSaveService {
    Image save(RawImage rawImage);

}
