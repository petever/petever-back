package com.example.petever.domain.post.application;

import com.example.petever.domain.post.model.Image;
import com.example.petever.domain.post.model.RawImage;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Component
public class MultipartFileToImageConvertor {

    public RawImage getImage(MultipartFile multipartFile) throws IOException {
        if (!multipartFile.getContentType().contains("image")) {
            throw new RuntimeException("image 를 입력해주세요");
        }
        var inputStream = new ByteArrayInputStream(multipartFile.getBytes());
        return new RawImage(Objects.requireNonNull(multipartFile.getResource().getFilename()),
                multipartFile.getSize(),
                inputStream.readAllBytes()
        );
    }
}
