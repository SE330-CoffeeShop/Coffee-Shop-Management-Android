package com.example.coffeeshopmanagementandroid.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coffeeshopmanagementandroid.data.api.ProductService;
import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.domain.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductService productService;

    public ProductRepositoryImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public LiveData<List<ProductModel>> getProducts() {
        return null;
    }

    @Override
    public LiveData<List<ProductModel>> getRecentProducts() {
        return null;
    }

    @Override
    public LiveData<List<CategoryModel>> getCategories() {
        return null;
    }
}