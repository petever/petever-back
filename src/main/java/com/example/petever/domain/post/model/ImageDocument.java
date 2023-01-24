package com.example.petever.domain.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("images")
@NoArgsConstructor
@Getter
public class ImageDocument {
    @Id
    private ObjectId id;
    private Image image;

    public ImageDocument(Image image) {
        this.id = new ObjectId();
        this.image = image;
    }
}
