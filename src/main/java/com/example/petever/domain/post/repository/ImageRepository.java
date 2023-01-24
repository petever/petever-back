package com.example.petever.domain.post.repository;

import com.example.petever.domain.post.model.ImageDocument;
import com.example.petever.domain.post.model.RawImage;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.Optional;

public interface ImageRepository {
    ImageDocument save(RawImage image) throws IOException;
    Optional<ImageDocument> findById(ObjectId id);
}
