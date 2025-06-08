package com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.response;

public class FavoriteProductResponse {
    private String favoriteProductId;

    public FavoriteProductResponse(String favoriteProductId) {
        this.favoriteProductId = favoriteProductId;
    }

    public String getFavoriteProductId() {
        return favoriteProductId;
    }

    public void setFavoriteProductId(String favoriteProductId) {
        this.favoriteProductId = favoriteProductId;
    }
    //    private String favoriteDrinkId;
//    private String productId;
//    private String userId;
//    public FavoriteProductResponse(String favoriteDrinkId, String productId, String userId) {
//        this.favoriteDrinkId = favoriteDrinkId;
//        this.productId = productId;
//        this.userId = userId;
//    }
//    public String getFavoriteDrinkId() {
//        return favoriteDrinkId;
//    }
//
//    public void setFavoriteDrinkId(String favoriteDrinkId) {
//        this.favoriteDrinkId = favoriteDrinkId;
//    }
//
//    public String getProductId() {
//        return productId;
//    }
//
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
}
