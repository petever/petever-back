package com.example.petever.domain.community.repository;

import com.example.petever.domain.community.model.CategoryDocument;
import com.example.petever.domain.community.model.CategoryType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;

public interface MongoCategoryRepository extends MongoRepository<CategoryDocument, ObjectId>, CategoryRepository {
    Stream<CategoryDocument> findAllByCategoryTypeAndOwnerId(CategoryType categoryType,String ownerId);
}
