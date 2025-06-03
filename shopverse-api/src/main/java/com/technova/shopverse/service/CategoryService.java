package com.technova.shopverse.service;

import com.technova.shopverse.dto.CategoryDTO;
import com.technova.shopverse.model.Category;

import java.util.List;
import java.util.Optional;
public interface CategoryService {
    List<CategoryDTO> getAllCategoryDTOs();
    Optional<CategoryDTO> getCategoryDTOById(Long id);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}