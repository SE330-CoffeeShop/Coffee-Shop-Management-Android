package com.example.coffeeshopmanagementandroid.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;

import java.util.List;

public interface ProductRepository {
    LiveData<List<ProductModel>> getProducts();
    LiveData<List<ProductModel>> getRecentProducts();
    LiveData<List<CategoryModel>> getCategories(); // Tách ra
}