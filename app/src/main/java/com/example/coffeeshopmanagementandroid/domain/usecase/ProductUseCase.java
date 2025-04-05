package com.example.coffeeshopmanagementandroid.domain.usecase;

import androidx.lifecycle.LiveData;

import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.domain.repository.ProductRepository;

import java.util.List;

public class ProductUseCase {
    private final ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public LiveData<List<ProductModel>> getProducts() {
        return productRepository.getProducts();
    }

    public LiveData<List<ProductModel>> getRecentProducts() {
        return productRepository.getRecentProducts();
    }

    public LiveData<List<CategoryModel>> getCategories() {
        return productRepository.getCategories();
    }
}
