package com.example.petever.domain.post.service;

import com.example.petever.config.FileConfiguration;
import com.example.petever.domain.post.model.Image;
import com.example.petever.domain.post.model.RawImage;
import com.example.petever.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EC2FileSaveService implements RawImageSaveService {

    private final FileConfiguration fileConfiguration;

    @Override
    public Image save(RawImage rawImage) {
        byte[] images = rawImage.getImage();
        RawImage thumbnailImage = rawImage.thumbnail(300, 300);
        String originImagePath = FileUtil.save(images, rawImage.getFileName(), rawImage.getExt(), fileConfiguration);
        String thumbnailImagePath = FileUtil.save(thumbnailImage.getImage(), thumbnailImage.getFileName(), thumbnailImage.getExt(), fileConfiguration);
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
