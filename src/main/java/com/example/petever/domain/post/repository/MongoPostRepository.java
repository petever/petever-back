package com.example.petever.domain.post.repository;

import com.example.petever.domain.post.model.Post;
import com.example.petever.domain.post.model.PostDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoPostRepository extends MongoRepository<PostDocument, ObjectId>, PostRepository {
}
