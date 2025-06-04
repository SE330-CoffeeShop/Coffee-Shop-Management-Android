package com.example.coffeeshopmanagementandroid.domain.usecase;

import android.util.Log;

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

    public List<ProductModel> getAllProducts(int page, int limit, String sortType, String sortBy) throws Exception {
        Log.d("Product Use Case", "Called");
        return productRepository.getAllProducts(page,limit, sortType, sortBy);
    }

    public List<ProductModel> getRecentProducts() throws Exception {
        return productRepository.getAllRecentProducts();
    }
}
