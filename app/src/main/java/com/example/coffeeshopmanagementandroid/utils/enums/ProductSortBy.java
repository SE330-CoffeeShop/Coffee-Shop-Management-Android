package com.example.coffeeshopmanagementandroid.utils.enums;

public enum ProductSortBy {
    NAME("productName"),
    PRICE("productPrice"),
    RATING("productRatingsAverage"),
    CREATED_AT("createdAt");

    private final String sortByField;

    ProductSortBy(String sortByField) {
        this.sortByField = sortByField;
    }

    public String getSortByField() {
        return sortByField;
    }

    @Override
    public String toString() {
        return sortByField;
    }
}
