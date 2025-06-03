package com.technova.shopverse.service.impl;

import com.technova.shopverse.dto.CategoryDTO;
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
    public List<CategoryDTO> getAllCategoryDTOs() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public Optional<CategoryDTO> getCategoryDTOById(Long id) {
        return categoryRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }

        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        Category saved = categoryRepository.save(category);
        return mapToDTO(saved);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría con ID " + id + " no encontrada."));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        Category updated = categoryRepository.save(category);
        return mapToDTO(updated);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // 🔁 Mapper Entity -> DTO
    private CategoryDTO mapToDTO(Category category) {
        List<String> productNames = category.getProducts().stream()
                .map(product -> product.getName())
                .toList();

        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                productNames
        );
    }
}