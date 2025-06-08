package com.example.coffeeshopmanagementandroid.data.repository;

import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.api.FavoriteProductService;
import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.request.GetAllFavoriteProductsUserRequest;
import com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.response.FavoriteProductResponse;
import com.example.coffeeshopmanagementandroid.domain.repository.FavoriteProductRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FavoriteProductRepositoryImpl implements FavoriteProductRepository {
    private final FavoriteProductService favoriteProductService;
    public FavoriteProductRepositoryImpl(FavoriteProductService favoriteProductService) {
        this.favoriteProductService = favoriteProductService;
    }
    @Override
    public BasePagingResponse<List<String>> getAllFavoriteProducts(GetAllFavoriteProductsUserRequest request) throws Exception {
        Log.d("Favorite Product RepoIml - getAllFavoriteProductsCustomer", "Called");
        Call<BasePagingResponse<List<String>>> call = favoriteProductService.getAllFavoriteProducts(request.getId(), request.getPage(), request.getLimit(), request.getSortType().toString(), request.getSortBy().toString());
        Response<BasePagingResponse<List<String>>> response = call.execute();
        if(response.isSuccessful() && response.body() != null) {
            Log.d("Response", String.valueOf(response.body().getData()));
            return response.body();
        } else {
            String errorMessage = "Get favorite products failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("GET FAVORITE PRODUCTS", errorMessage);
            throw new Exception(errorMessage);
        }
    }
}
