package com.example.coffeeshopmanagementandroid.utils.enums;

public enum CategorySortBy {
    NAME("categoryName"),
    CREATED_AT("createdAt");
    private final String sortByField;

    CategorySortBy(String sortByField) {
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
