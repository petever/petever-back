package com.example.petever.domain.post.service;

import com.example.petever.config.FileConfiguration;
import com.example.petever.domain.post.model.Image;
import com.example.petever.domain.post.model.RawImage;
import com.example.petever.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EC2FileSaveService implements RawImageSaveService {

    private final FileConfiguration fileConfiguration;

    @Override
    public Image save(RawImage rawImage) {
        byte[] images = rawImage.getImage();
        int thumbnailWidth = 0;
        int thumbnailHeight = 0;
        try (ByteArrayInputStream stream = new ByteArrayInputStream(rawImage.getImage())) {
            BufferedImage image = ImageIO.read(stream);

            BigDecimal width = BigDecimal.valueOf(image.getWidth());
            BigDecimal height = BigDecimal.valueOf(image.getHeight());

            if (width.compareTo(new BigDecimal("700")) <= 0) {
                thumbnailWidth = width.intValue();
            } else {
                thumbnailWidth = width.multiply(BigDecimal.valueOf(0.5)).intValue();
            }

            if (width.compareTo(new BigDecimal("700")) <= 0) {
                thumbnailHeight = height.intValue();
            } else {
                thumbnailHeight = height.multiply(BigDecimal.valueOf(0.5)).intValue();
            }
        } catch (IOException e) {
            throw new RuntimeException("Thumbnail Error", e);
        }
        RawImage thumbnailImage = rawImage.thumbnail(thumbnailWidth, thumbnailHeight);
        String originImagePath = FileUtil.save(images, rawImage.getFileName(), rawImage.getExt(), false, true, fileConfiguration);
        String thumbnailImagePath = FileUtil.save(thumbnailImage.getImage(), thumbnailImage.getFileName(), thumbnailImage.getExt(), false, true, fileConfiguration);
        return new Image(
                Map.of("fileName", rawImage.getFileName(),
                        "ext", rawImage.getExt(),
                        "fileFullName", rawImage.getFileFullName(),
                        "size", String.valueOf(rawImage.getSize())),
                thumbnailImagePath,
                originImagePath
        );
    }
}
