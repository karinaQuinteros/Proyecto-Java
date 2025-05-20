package com.technova.shopverse.controller;



import com.technova.shopverse.dto.CategoryDTO;
import com.technova.shopverse.model.Category;
import com.technova.shopverse.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import java.util.List;



@RestController

@RequestMapping("/api/categories")

public class CategoryController {

    @Autowired

    private CategoryService categoryService;

    @GetMapping("/{id}/details")
    public ResponseEntity<CategoryDTO> getCategoryDetails(@PathVariable Long id) {
        try {
            CategoryDTO dto = categoryService.getCategoryDTOById(id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping

    public ResponseEntity<List<Category>> getAll() {

        List<Category> category = categoryService.getAllCategory();


        if (category.isEmpty()) {

            return ResponseEntity.noContent().build(); // 204 No Content

        } else {

            return ResponseEntity.ok(category); // 200 OK

        }

    }


    @GetMapping("/{id}")

    public ResponseEntity<Category> getById(@PathVariable Long id) {

        return categoryService.getCategoryById(id)

                .map(ResponseEntity::ok)

                .orElse(ResponseEntity.notFound().build());

    }


    @PostMapping

    public ResponseEntity<Category> create(@Valid @RequestBody Category category) {

        Category created = categoryService.createCategory(category);

        return ResponseEntity.status(201).body(created);

    }


    @PutMapping("/{id}")

    public ResponseEntity<Category> update(@Valid @PathVariable Long id, @RequestBody Category category) {

        try {

            Category updated = categoryService.updateCategory(id, category);

            return ResponseEntity.ok(updated); // 200 OK

        } catch (IllegalArgumentException e) {

            return ResponseEntity.notFound().build(); // 404 Not Found si no existe

        }

    }


    @DeleteMapping("/{id}")

    public ResponseEntity<Void> delete(@PathVariable Long id) {

        try {

            categoryService.deleteCategory(id);

            return ResponseEntity.noContent().build(); // 204 No Content

        } catch (IllegalArgumentException e) {

            return ResponseEntity.notFound().build(); // 404 Not Found

        }

    }
}