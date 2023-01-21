package com.example.petever.domain.post.model;

import lombok.Value;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Value
public class RawImage {
    String fileFullName;
    String fileName;

    String ext;
    Long size;
    byte[] image;

    public RawImage(String fileFullName, Long size, byte[] image) {
        int extIndex = fileFullName.lastIndexOf(".");
        this.fileName = fileFullName.substring(0, extIndex);
        this.ext = fileFullName.substring(extIndex + 1);
        this.size = size;
        this.image = image;
        this.fileFullName = fileFullName;
    }

    private RawImage(byte[] image, RawImage rawImage) {
        this.image = image;
        this.ext = rawImage.ext;
        this.size = rawImage.size;
        this.fileName = "thumbnail_" + rawImage.fileName;
        this.fileFullName = "thumbnail_" + rawImage.fileFullName;
    }

    public RawImage thumbnail(int width, int height) {
        final RawImage rawImage;
        try (ByteArrayInputStream originInputStream = new ByteArrayInputStream(image)) {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                Thumbnails.of(originInputStream)
                        .size(width, height)
                        .outputFormat(ext)
                        .outputQuality(1)
                        .toOutputStream(outputStream);
                rawImage = new RawImage(outputStream.toByteArray(), this);
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Image 를 저장하지 못했습니다");
        }
        return rawImage;
    }
}
