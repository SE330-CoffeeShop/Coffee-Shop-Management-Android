package com.example.coffeeshopmanagementandroid.data.api;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.response.FavoriteProductResponse;
import com.example.coffeeshopmanagementandroid.data.dto.product.response.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FavoriteProductService {
    @GET("/favorite-drinks/user/{userId}")
    Call<BasePagingResponse<List<String>>> getAllFavoriteProducts(@Path("userId") String userId,
                                                                                   @Query("page") int page,
                                                                                   @Query("limit") int limit,
                                                                                   @Query("sortType") String sortType,
                                                                                   @Query("sortBy") String sortBy
    );
}
