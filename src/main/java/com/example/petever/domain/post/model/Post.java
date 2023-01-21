package com.example.petever.domain.post.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.List;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Transient
    private String id;
    @Transient
    private String categoryId;

    @Transient
    private boolean isTemp;

    private String content;

    private List<ObjectId> images;
    private List<ObjectId> files;

    public Post post(ObjectId id, ObjectId categoryId) {
        this.id = id.toHexString();
        this.categoryId = categoryId.toHexString();
        return this;
    }
}
