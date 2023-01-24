package com.example.petever.domain.post.application;

import com.example.petever.domain.post.model.Image;
import com.example.petever.domain.post.model.ImageDocument;
import com.example.petever.domain.post.model.RawImage;
import com.example.petever.domain.post.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    public ObjectId uploadImage(RawImage rawImage) throws IOException {
        return imageRepository.save(rawImage).getId();
    }
    public Image getImageInfo(String imageId) {
        return imageRepository.findById(new ObjectId(imageId)).orElseThrow().getImage();
    }
}
