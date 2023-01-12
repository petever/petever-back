package com.example.petever.domain.community.web.request;

import lombok.Value;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {

    private CategoryRequest() {
    }

    @Value
    public static class CreateCategoryRequest {
        @NotBlank
        String categoryName;
    }
}
