package com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.request;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingRequest;
import com.example.coffeeshopmanagementandroid.utils.enums.sortBy.FavoriteProductSortBy;
import com.example.coffeeshopmanagementandroid.utils.enums.SortType;
import com.google.gson.annotations.SerializedName;

public class GetAllFavoriteProductsUserRequest extends BasePagingRequest {
    @SerializedName("userId")
    private String userId;
    @SerializedName("sortType")
    private SortType sortType;
    @SerializedName("sortBy")
    private FavoriteProductSortBy sortBy;
    public GetAllFavoriteProductsUserRequest(int page, int limit, String id, SortType sortType, FavoriteProductSortBy sortBy) {
        super(page, limit);
        this.userId = id;
        this.sortType = sortType;
        this.sortBy = sortBy;
    }

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public FavoriteProductSortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(FavoriteProductSortBy sortBy) {
        this.sortBy = sortBy;
    }
}
