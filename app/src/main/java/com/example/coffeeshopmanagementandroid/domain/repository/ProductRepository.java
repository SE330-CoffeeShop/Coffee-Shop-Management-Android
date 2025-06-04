package com.example.coffeeshopmanagementandroid.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;

import java.util.List;

public interface ProductRepository {
    List<ProductModel> getAllProducts(int page, int limit, String sortType, String sortBy) throws Exception;

    List<ProductModel> getAllRecentProducts() throws Exception;
}