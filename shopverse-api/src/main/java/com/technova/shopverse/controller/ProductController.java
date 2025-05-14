package com.technova.shopverse.controller;



import com.technova.shopverse.model.Product;

import com.technova.shopverse.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

import java.util.Optional;



@RestController

@RequestMapping("/api/products")

public class ProductController {



    @Autowired

    private ProductService productService;



    @GetMapping

    public ResponseEntity<List<Product>> getAll() {

        List<Product> products = productService.getAllProducts();



        if (products.isEmpty()) {

            return ResponseEntity.noContent().build(); // 204 No Content

        } else {

            return ResponseEntity.ok(products); // 200 OK

        }

    }



    @GetMapping("/{id}")

    public ResponseEntity<Product> getById(@PathVariable Long id) {

        return productService.getProductById(id)

                .map(ResponseEntity::ok)

                .orElse(ResponseEntity.notFound().build());

    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        try {

            product.setId(null);

            Product created = productService.createProduct(product);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear el producto: " + e.getMessage());
        }
    }




    @PutMapping("/{id}")

    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {

        try {

            Product updated = productService.updateProduct(id, product);

            return ResponseEntity.ok(updated); // 200 OK

        } catch (IllegalArgumentException e) {

            return ResponseEntity.notFound().build(); // 404 Not Found si no existe

        }

    }



    @DeleteMapping("/{id}")

    public ResponseEntity<Void> delete(@PathVariable Long id) {

        try {

            productService.deleteProduct(id);

            return ResponseEntity.noContent().build(); // 204 No Content

        } catch (IllegalArgumentException e) {

            return ResponseEntity.notFound().build(); // 404 Not Found

        }

    }
}