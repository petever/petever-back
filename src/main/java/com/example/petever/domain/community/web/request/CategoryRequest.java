package com.example.petever.domain.community.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {

    private CategoryRequest() {
    }

    @Data
    public static class CreateCategoryRequest {
        @NotBlank
        @JsonProperty("categoryName")
        private String categoryName;

    }
}
