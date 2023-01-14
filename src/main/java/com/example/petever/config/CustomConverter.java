package com.example.petever.config;

import com.example.petever.serializer.CustomLocalDateTimeSerializer;
import com.example.petever.serializer.ObjectIdSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class CustomConverter extends SimpleModule {
    public CustomConverter() {
        super("customConverter");
        addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        addSerializer(ObjectId.class, new ObjectIdSerializer());
    }
}
