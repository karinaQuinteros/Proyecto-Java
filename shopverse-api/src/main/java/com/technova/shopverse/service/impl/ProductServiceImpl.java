package com.technova.shopverse.service.impl;

import com.technova.shopverse.dto.ProductDTO;
import com.technova.shopverse.model.Product;
import com.technova.shopverse.repository.ProductRepository;
import com.technova.shopverse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProductDTOs() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public Optional<ProductDTO> getProductDTOById(Long id) {
        return productRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = toEntity(dto);
        Product saved = productRepository.save(product);
        return toDTO(saved);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID " + id));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return toDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Producto con ID " + id + " no encontrado.");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(this::toDTO)
                .toList();
    }



    private ProductDTO toDTO(Product product) {
        String categoryName = product.getCategory() != null ? product.getCategory().getName() : null;
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), categoryName);
    }

    private Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        return product;
    }
}
