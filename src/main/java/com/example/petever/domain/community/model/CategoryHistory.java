package com.example.petever.domain.community.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryHistory {

    public enum CategoryHistoryType {
        DELETE, CHANGE
    }

    private CategoryType categoryType;
    private String beforeCategoryName;
    private String changeUserId;

    private ZonedDateTime changedAt;

    private CategoryHistoryType categoryHistoryType;

    @Builder
    public CategoryHistory(String beforeCategoryName, String changeUserId, CategoryType categoryType, CategoryHistoryType categoryHistoryType) {
        this.beforeCategoryName = beforeCategoryName;
        this.changeUserId = changeUserId;
        this.changedAt = ZonedDateTime.now();
        this.categoryHistoryType = categoryHistoryType;
    }

    public static CategoryHistory changeHistoryByCategoryType(CategoryType beforeCategoryType, String changeUserId) {
        return CategoryHistory.builder()
                .categoryType(beforeCategoryType)
                .changeUserId(changeUserId)
                .categoryHistoryType(CategoryHistoryType.CHANGE)
                .build();
    }

    public static CategoryHistory changeHistoryByCategoryName(String beforeCategoryName, String changeUserId) {
        return CategoryHistory.builder()
                .beforeCategoryName(beforeCategoryName)
                .changeUserId(changeUserId)
                .categoryHistoryType(CategoryHistoryType.CHANGE)
                .build();
    }

    public static CategoryHistory deleteHistory(String deleteUserId) {
        return CategoryHistory.builder()
                .changeUserId(deleteUserId)
                .categoryHistoryType(CategoryHistoryType.CHANGE)
                .build();
    }
}
