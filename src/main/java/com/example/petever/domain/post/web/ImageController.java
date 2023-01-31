package com.example.petever.domain.post.web;

import com.example.petever.domain.post.application.ImageService;
import com.example.petever.domain.post.application.MultipartFileToImageConvertor;
import com.example.petever.domain.post.model.Image;
import com.example.petever.domain.post.model.RawImage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/image")
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class ImageController {
    private final ImageService imageService;
    private final MultipartFileToImageConvertor multipartFileToImageConvertor;

    @GetMapping("/{id}")
    public Image getImageInfo(@PathVariable(name = "id") String imageId) {
        return this.imageService.getImageInfo(imageId);
    }

    @PostMapping("")
    public String uploadImageServer(@RequestParam MultipartFile image) {
        try {
            RawImage rawImage = multipartFileToImageConvertor.getImage(image);
            return "/api/image/" + imageService.uploadImage(rawImage).toHexString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
