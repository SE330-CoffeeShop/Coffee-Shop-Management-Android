package com.example.coffeeshopmanagementandroid.domain.usecase;

import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.request.GetAllFavoriteProductsUserRequest;
import com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.response.FavoriteProductResponse;
import com.example.coffeeshopmanagementandroid.domain.repository.FavoriteProductRepository;

import java.util.List;

public class FavoriteProductUseCase {
    private final FavoriteProductRepository favoriteProductRepository;
    public FavoriteProductUseCase(FavoriteProductRepository favoriteProductRepository) {
        this.favoriteProductRepository = favoriteProductRepository;
    }
    public BasePagingResponse<List<String>> getAllFavoriteProducts(GetAllFavoriteProductsUserRequest request) throws Exception {
        Log.d("Favorite Product Use Case", "Called");
        return favoriteProductRepository.getAllFavoriteProducts(request);
    }
}
