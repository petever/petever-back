package com.example.petever.domain.community.application;

import com.example.petever.domain.community.model.Category;
import com.example.petever.domain.community.model.CategoryDocument;
import com.example.petever.domain.community.model.CategoryType;
import com.example.petever.domain.community.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(CategoryType categoryType, String ownerId) {
        return categoryRepository
                .findAllByCategoryTypeAndOwnerIdAndDeletedFalse(categoryType, ownerId)
                .map(CategoryDocument::getCategory)
                .collect(Collectors.toList());
    }

    public Category createCategory(CategoryType categoryType, String ownerId, String categoryName) {
        return categoryRepository.save(CategoryDocument.builder()
                        .categoryName(categoryName)
                        .categoryType(categoryType)
                        .ownerId(ownerId)
                        .build())
                .getCategory();
    }

    public Category createCategoryChild(String id, String categoryName) {
        return categoryRepository.save(categoryRepository.findById(id)
                        .orElseThrow()
                        .createChild(categoryName))
                .getCategory();
    }

    public Category changeCategoryType(String id, CategoryType categoryType, String changeUserId) {
        return categoryRepository.save(categoryRepository.findById(id)
                .orElseThrow()
                .changeCategoryType(categoryType, changeUserId)).getCategory();
    }

    public Category changeCategoryName(String id, String categoryName, String changeUserId) {
        return categoryRepository.save(categoryRepository.findById(id)
                        .orElseThrow()
                        .changeCategoryName(categoryName, changeUserId))
                .getCategory();
    }

    public Category deleteCategory(String id, String deleteUserId) {
        return categoryRepository.save(categoryRepository
                .findById(id)
                .orElseThrow()
                .removeCategory(deleteUserId)).getCategory();
    }
}
