package com.example.petever.domain.community.repository;

import com.example.petever.domain.community.model.CategoryDocument;
import com.example.petever.domain.community.model.CategoryType;

import java.util.Optional;
import java.util.stream.Stream;

public interface CategoryRepository {
    Stream<CategoryDocument> findAllByCategoryTypeAndOwnerId(CategoryType categoryType, String ownerId);

    Optional<CategoryDocument> findById(String id);

    CategoryDocument save(CategoryDocument categoryDocument);
}
