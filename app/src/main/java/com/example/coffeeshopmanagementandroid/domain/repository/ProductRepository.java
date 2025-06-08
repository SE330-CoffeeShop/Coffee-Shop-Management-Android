package com.example.coffeeshopmanagementandroid.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.product.request.GetAllProductsRequest;
import com.example.coffeeshopmanagementandroid.data.dto.product.response.ProductResponse;
import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;

import java.util.List;

public interface ProductRepository {
    BasePagingResponse<List<ProductResponse>> getAllProducts(GetAllProductsRequest request) throws Exception;

    List<ProductModel> getAllRecentProducts() throws Exception;
    ProductModel getProductById(String id) throws Exception;
}