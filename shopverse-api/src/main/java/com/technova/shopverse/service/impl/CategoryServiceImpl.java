package com.technova.shopverse.service.impl;

import com.technova.shopverse.model.Category;
import com.technova.shopverse.repository.CategoryRepository;
import com.technova.shopverse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        if (category.getName() == null || category.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category updated) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException("El producto con ID " + id + " no existe.");
        }
        Category category = optionalCategory.get();
        category.setName(updated.getName());
        category.setDescription(updated.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
