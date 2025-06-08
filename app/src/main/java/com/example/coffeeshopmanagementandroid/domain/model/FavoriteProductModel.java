package com.example.coffeeshopmanagementandroid.domain.model;

public class FavoriteProductModel {
    private String id;
    public FavoriteProductModel() {
        // Constructor mặc định
    }
    public FavoriteProductModel(String favoriteProductId) {
        this.id = favoriteProductId;
    }

    public String getFavoriteProductId() {
        return id;
    }

    public void setFavoriteProductId(String favoriteProductId) {
        this.id = favoriteProductId;
    }
}
