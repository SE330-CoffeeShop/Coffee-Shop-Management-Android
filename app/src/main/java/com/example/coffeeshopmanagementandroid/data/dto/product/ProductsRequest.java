package com.example.coffeeshopmanagementandroid.data.dto.product;

import com.google.gson.annotations.SerializedName;

public class ProductsRequest {
    @SerializedName("page")
    private int page;
    @SerializedName("limit")
    private int limit;
    @SerializedName("sortType")
    private String sortType;
    @SerializedName("sortBy")
    private String sortBy;

    public ProductsRequest(int page, int limit, String sortType, String sortBy) {
        this.page = page;
        this.limit = limit;
        this.sortType = sortType;
        this.sortBy = sortBy;
    }
}
