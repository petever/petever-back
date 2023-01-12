package com.example.petever.domain.category.repository;

import com.example.petever.PeteverApplication;
import com.example.petever.domain.community.model.CategoryDocument;
import com.example.petever.domain.community.model.CategoryType;
import com.example.petever.domain.community.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Category Update 를 수행하고 save 처리 시, 변경사항을 저장하고, 가져올 수 있다")
    void createCategoryTest() {
        CategoryDocument document = categoryRepository.save(CategoryDocument.builder()
                .ownerId("test_1")
                .categoryType(CategoryType.DEFAULT)
                .categoryName("categoryName")
                .level(null)
                .parentId(null)
                .build());

        categoryRepository.save(document.changeCategoryName("category_name_2", "change_user_id"));
        CategoryDocument changeDocument = this.getCategoryTest(document.getId().toHexString(), document);

        assertThat(changeDocument.getHistories()).hasSize(1);
        assertThat(changeDocument.getCategoryType()).isEqualTo(CategoryType.DEFAULT);
        assertThat(changeDocument.getCategory()).isEqualTo(document.getCategory());
        assertThat(changeDocument.getOwnerId()).isEqualTo(document.getOwnerId());
        assertThat(changeDocument.getDeleted()).isFalse();
    }

    CategoryDocument getCategoryTest(String externalId, CategoryDocument categoryDocument) {
        String id = externalId;
        if (id == null) {
            categoryDocument = CategoryDocument.builder()
                    .ownerId("test_1")
                    .categoryType(CategoryType.DEFAULT)
                    .categoryName("categoryName")
                    .level(null)
                    .parentId(null)
                    .build();
            id = categoryRepository.save(categoryDocument)
                    .getId()
                    .toHexString();
        }
        CategoryDocument document = categoryRepository.findById(id).orElseThrow();

        assertThat(document.getCategory()).isEqualTo(categoryDocument.getCategory());
        assertThat(document.getCategoryType()).isEqualTo(categoryDocument.getCategoryType());
        assertThat(document.getId()).isEqualTo(categoryDocument.getId());
        assertThat(document.getOwnerId()).isEqualTo(categoryDocument.getOwnerId());

        return document;
    }
}
