package com.example.petever.domain.post.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;

@Document(collation = "posts")
@AllArgsConstructor
@NoArgsConstructor
public class PostDocument {
    private ObjectId id;
    private ObjectId categoryId;
    @Embedded
    private Post post;
}
