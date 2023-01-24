package com.example.petever.domain.post.repository;

import com.example.petever.domain.post.model.ImageDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoImageRepository extends MongoRepository<ImageDocument, ObjectId> {
}
