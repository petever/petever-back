package com.example.petever.domain.community.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.CompoundIndex;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id", "categoryName", "parentId", "level"})
@CompoundIndex(def = "{'categoryName': 1, 'level': 1, 'parentId': 1}", unique = true)
public class Category {
    @Transient
    private ObjectId id;

    private Long level;

    private ObjectId parentId;
    private String categoryName;
    private LocalDateTime createdAt;

    @Builder
    public Category(Long level, ObjectId parentId, String categoryName) {
        this.id = null;
        this.level = level == null ? 0L : level;
        this.parentId = parentId;
        this.categoryName = categoryName;
        this.createdAt = LocalDateTime.now();
    }

    public void changeParent(ObjectId parentId) {
        this.parentId = parentId;
    }

    public void changeCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category category(ObjectId id) {
        this.id = id;
        return this;
    }
}
