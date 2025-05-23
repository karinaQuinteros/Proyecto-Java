package com.technova.shopverse.service;

import com.technova.shopverse.dto.ProductDTO;
import com.technova.shopverse.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    ProductDTO toDTO(Product product);
    List<ProductDTO> getByCategoryId(Long categoryId);
    List<ProductDTO> getAllProductDTOs();


}
