package com.example.coffeeshopmanagementandroid.data.repository;

import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.api.ProductService;
import com.example.coffeeshopmanagementandroid.data.dto.product.ProductsRequest;
import com.example.coffeeshopmanagementandroid.data.dto.product.ProductsResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.AuthMapper;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.domain.repository.ProductRepository;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductService productService;

    public ProductRepositoryImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductModel> getAllProducts(int page, int limit, String sortType, String sortBy) throws Exception {
        Log.d("Product RepoIml - getAllProducts", "Called");
        Call<ProductsResponse> call = productService.getAllProducts(new ProductsRequest(page, limit, sortType, sortBy));
        Response<ProductsResponse> response = call.execute();

        if (response.isSuccessful() && response.body() != null) {
            Log.d("Response", String.valueOf(response.body().getData()));
            return response.body().getData();
        } else {
            String errorMessage = "Get products failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("GET PRODUCTS", errorMessage);
            throw new Exception(errorMessage);
        }
    }

    @Override
    public List<ProductModel> getAllRecentProducts() {
        return null;
    }
}