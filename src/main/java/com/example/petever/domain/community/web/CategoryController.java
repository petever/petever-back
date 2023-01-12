package com.example.petever.domain.community.web;

import com.example.petever.domain.community.application.CategoryService;
import com.example.petever.domain.community.model.Category;
import com.example.petever.domain.community.model.CategoryType;
import com.example.petever.domain.community.web.request.CategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<Category> getCategories() {
        return categoryService.getCategories(CategoryType.DEFAULT, "admin");
    }

    @PostMapping("/{id}/name/{categoryName}")
    public Category changeCategoryName(@PathVariable("id") String categoryId, @PathVariable("categoryName") String categoryName) {
        return categoryService.changeCategoryName(categoryId, categoryName, "admin");
    }

    @PostMapping("/{id}/type/{categoryType}")
    public Category changeCategoryType(@PathVariable("id") String categoryId, @PathVariable("categoryType") CategoryType categoryType) {
        return categoryService.changeCategoryType(categoryId, categoryType, "admin");
    }

    @PostMapping()
    public Category createCategory(@Valid @RequestBody CategoryRequest.CreateCategoryRequest request) {
        return categoryService.createCategory(CategoryType.DEFAULT, "admin", request.getCategoryName());
    }

    @PostMapping("/{id}/child")
    public Category createCategoryChild(
            @PathVariable("id") String id,
            @Valid @RequestBody CategoryRequest.CreateCategoryRequest request) {
        return categoryService.createCategoryChild(id, request.getCategoryName());
    }
}
