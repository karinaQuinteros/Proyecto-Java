package com.technova.shopverse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres")
    private String description;
    @OneToMany(mappedBy = "category")
    @JsonBackReference

    private List<Product> products;
    public Category() {}


    public Category(Long id, String name, String description) {

        this.id = id;
        this.name = name;
        this.description = description;

    }
    // Getters y Setters
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}