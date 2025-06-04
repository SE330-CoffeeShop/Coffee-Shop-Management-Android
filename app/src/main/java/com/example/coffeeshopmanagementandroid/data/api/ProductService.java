package com.example.coffeeshopmanagementandroid.data.api;

import com.example.coffeeshopmanagementandroid.data.dto.product.ProductsRequest;
import com.example.coffeeshopmanagementandroid.data.dto.product.ProductsResponse;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {
    @GET("/product/all")
    Call<ProductsResponse> getAllProducts(@Body ProductsRequest request);

    @GET("/product/{id}")
    Call<ProductModel> getProductById(@Path("id") String id);
}
