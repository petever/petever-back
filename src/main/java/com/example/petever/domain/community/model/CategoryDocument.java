package com.example.petever.domain.community.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDocument {

    @Id
    private ObjectId id;
    private String ownerId;
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
    @Embedded
    @Indexed
    private Category category;
    private Boolean deleted;
    private LocalDateTime deletedAt;
    private List<CategoryHistory> histories = new ArrayList<>();

    @Builder
    public CategoryDocument(CategoryType categoryType, String categoryName, String ownerId, Long level, ObjectId parentId) {
        this.id = new ObjectId();
        this.category = Category.builder()
                .categoryName(categoryName)
                .level(level)
                .parentId(parentId)
                .build();
        this.categoryType = categoryType;
        this.ownerId = ownerId;
        this.deleted = false;
    }

    public CategoryDocument createChild(String categoryName) {
        return CategoryDocument.builder()
                .categoryType(this.categoryType)
                .categoryName(categoryName)
                .ownerId(this.ownerId)
                .level(this.category.getLevel() + 1)
                .parentId(this.id)
                .build();
    }

    public CategoryDocument changeCategoryName(String categoryName, String changeUserId) {
        String beforeCategoryName = this.category.getCategoryName();
        this.category.changeCategoryName(categoryName);
        this.histories.add(CategoryHistory.changeHistoryByCategoryName(beforeCategoryName, changeUserId));
        return this;
    }

    public CategoryDocument removeCategory(String deleteUserId) {
        this.deletedAt = LocalDateTime.now();
        this.deleted = true;
        this.histories.add(CategoryHistory.deleteHistory(deleteUserId));
        return this;
    }

    public CategoryDocument changeCategoryType(CategoryType categoryType, String changeUserId) {
        CategoryType beforeCategoryType = this.categoryType;
        this.categoryType = categoryType;
        this.histories.add(CategoryHistory.changeHistoryByCategoryType(beforeCategoryType, changeUserId));
        return this;
    }

    public Category getCategory() {
        return category.getCategory(this.id.toHexString());
    }
}
