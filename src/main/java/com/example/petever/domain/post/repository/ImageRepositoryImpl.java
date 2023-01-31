package com.example.petever.domain.post.repository;

import com.example.petever.domain.post.model.ImageDocument;
import com.example.petever.domain.post.model.RawImage;
import com.example.petever.domain.post.service.RawImageSaveService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Primary
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {
    private final MongoImageRepository mongoImageRepository;
    private final RawImageSaveService rawImageSaveService;

    @Override
    public ImageDocument save(RawImage image) throws IOException {
        return mongoImageRepository.save(new ImageDocument(rawImageSaveService.save(image)));
    }

    @Override
    public Optional<ImageDocument> findById(ObjectId id) {
        return this.mongoImageRepository.findById(id);
    }
}
