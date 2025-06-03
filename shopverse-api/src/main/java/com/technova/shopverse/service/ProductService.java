package com.technova.shopverse.service;

import com.technova.shopverse.dto.ProductDTO;
import com.technova.shopverse.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<ProductDTO> getAllProductDTOs();
    Optional<ProductDTO> getProductDTOById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> getByCategoryId(Long categoryId);
}