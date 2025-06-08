package com.example.coffeeshopmanagementandroid.data.repository;

import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.api.ProductService;
import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.BaseResponse;
import com.example.coffeeshopmanagementandroid.data.dto.product.request.GetAllProductsRequest;
import com.example.coffeeshopmanagementandroid.data.dto.product.response.ProductResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.ProductMapper;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.domain.repository.ProductRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductService productService;

    public ProductRepositoryImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public BasePagingResponse<List<ProductResponse>> getAllProducts(GetAllProductsRequest request) throws Exception {
        Log.d("Product RepoIml - getAllProducts", "Called");

        Call<BasePagingResponse<List<ProductResponse>>> call = productService.getAllProducts(request.getPage(), request.getLimit(), request.getSortType(), request.getSortBy());
        Response<BasePagingResponse<List<ProductResponse>>> response = call.execute();

        if (response.isSuccessful() && response.body() != null) {
            Log.d("Response", String.valueOf(response.body().getData()));
            return response.body();
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
    @Override
    public ProductModel getProductById(String id) throws Exception {
        Log.d("Product RepoIml - getDetailProduct", "Called");

        Call<BaseResponse<ProductResponse>> call = productService.getProductById(id);
        Response<BaseResponse<ProductResponse>> response = call.execute();
        if (response.isSuccessful() && response.body() != null) {
            Log.d("Response", String.valueOf(response.body()));
            return ProductMapper.mapProductResponseToProductDomain(response.body().getData());
        } else {
            String errorMessage = "Get detail product failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("GET DETAIL PRODUCT", errorMessage);
            throw new Exception(errorMessage);
        }
    }
}