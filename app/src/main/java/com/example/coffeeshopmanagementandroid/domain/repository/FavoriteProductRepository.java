package com.example.coffeeshopmanagementandroid.domain.repository;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.request.GetAllFavoriteProductsUserRequest;
import com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.response.FavoriteProductResponse;
import com.example.coffeeshopmanagementandroid.utils.enums.SortType;
import com.example.coffeeshopmanagementandroid.utils.enums.sortBy.FavoriteProductSortBy;

import java.util.List;

import retrofit2.Call;

public interface FavoriteProductRepository {
    BasePagingResponse<List<String>> getAllFavoriteProducts(GetAllFavoriteProductsUserRequest request) throws Exception;
}
