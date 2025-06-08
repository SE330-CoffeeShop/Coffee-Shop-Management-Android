package com.example.coffeeshopmanagementandroid.data.api;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.BaseResponse;
import com.example.coffeeshopmanagementandroid.data.dto.product.request.GetAllProductsRequest;
import com.example.coffeeshopmanagementandroid.data.dto.product.response.ProductResponse;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;

import java.util.List;

import javax.annotation.ParametersAreNullableByDefault;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @GET("/product/all")
    Call<BasePagingResponse<List<ProductResponse>>> getAllProducts(@Query("page") int page,
                                                                   @Query("limit") int limit,
                                                                   @Query("sortType") String sortType,
                                                                   @Query("sortBy") String sortBy
    );

    @GET("/product/{id}")
    Call<BaseResponse<ProductResponse>> getProductById(@Path("id") String id);
}
