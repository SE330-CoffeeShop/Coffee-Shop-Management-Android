package com.example.coffeeshopmanagementandroid.domain.model;

import java.util.Objects;

public class ProductModel {
    private String productId;
    private String productName;
    private double productPrice;
    private String productThumb;
    private float productRating;
    private String productCategory;
    private boolean isFavorite;
    private String productDescription;

    public ProductModel(String productId, String productName, String productDescription, double productPrice, String productThumb, float productRating, String productCategory, boolean isFavorite) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productThumb = productThumb;
        this.productRating = productRating;
        this.productCategory = productCategory;
        this.isFavorite = isFavorite;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(String productThumb) {
        this.productThumb = productThumb;
    }

    public float getProductRating() {
        return productRating;
    }

    public void setProductRating(float productRating) {
        this.productRating = productRating;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel that = (ProductModel) o;
        return Double.compare(that.productPrice, productPrice) == 0 &&
                Float.compare(that.productRating, productRating) == 0 &&
                isFavorite == that.isFavorite &&
                productId.equals(that.productId) &&
                productName.equals(that.productName) &&
                productThumb.equals(that.productThumb) &&
                productCategory.equals(that.productCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                productId,
                productName,
                productPrice,
                productThumb,
                productRating,
                productCategory,
                isFavorite
        );
    }
}

