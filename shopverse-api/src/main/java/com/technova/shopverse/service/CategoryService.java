package com.technova.shopverse.service;

import com.technova.shopverse.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategory();
    Optional<Category> getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category updated);
    void deleteCategory(Long id);
}