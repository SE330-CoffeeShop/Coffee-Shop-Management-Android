package com.example.coffeeshopmanagementandroid.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ProductModel {
    private String productId;
    private String productName;
    private String productThumb;
    private String productDescription;
    private BigDecimal productPrice;
    private String productSlug;
    private BigDecimal productRatingsAverage;
    private Boolean productIsPublished;
    private Boolean productIsDeleted;
    private String productCategoryId;

    // Default constructor
    public ProductModel() {
    }

    // Constructor đầy đủ các trường cần thiết (có thể tùy chỉnh)
    public ProductModel(String productId,
                        String productName,
                        String productDescription,
                        BigDecimal productPrice,
                        String productThumb,
                        BigDecimal productRatingsAverage,
                        String productCategoryId
                        ) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productThumb = productThumb;
        this.productRatingsAverage = productRatingsAverage;
        this.productCategoryId = productCategoryId;
    }

    // Getter / Setter

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

    public String getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(String productThumb) {
        this.productThumb = productThumb;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductRatingsAverage() {
        return productRatingsAverage;
    }

    public void setProductRatingsAverage(BigDecimal productRatingsAverage) {
        this.productRatingsAverage = productRatingsAverage;
    }

    public String getProductSlug() {
        return productSlug;
    }

    public void setProductSlug(String productSlug) {
        this.productSlug = productSlug;
    }

    public Boolean getProductIsPublished() {
        return productIsPublished;
    }

    public void setProductIsPublished(Boolean productIsPublished) {
        this.productIsPublished = productIsPublished;
    }

    public Boolean getProductIsDeleted() {
        return productIsDeleted;
    }

    public void setProductIsDeleted(Boolean productIsDeleted) {
        this.productIsDeleted = productIsDeleted;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    // equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductModel)) return false;
        ProductModel that = (ProductModel) o;
        return
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productThumb, that.productThumb) &&
                Objects.equals(productDescription, that.productDescription) &&
                Objects.equals(productPrice, that.productPrice) &&
                Objects.equals(productRatingsAverage, that.productRatingsAverage) &&
                Objects.equals(productCategoryId, that.productCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productThumb, productDescription, productPrice, productRatingsAverage, productCategoryId);
    }
}