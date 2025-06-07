package com.example.coffeeshopmanagementandroid.data.dto.order.request;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingRequest;
import com.example.coffeeshopmanagementandroid.utils.enums.OrderSortBy;
import com.example.coffeeshopmanagementandroid.utils.enums.ProductSortBy;
import com.example.coffeeshopmanagementandroid.utils.enums.SortType;
import com.google.gson.annotations.SerializedName;

public class GetAllOrdersCustomerRequest extends BasePagingRequest {
    @SerializedName("customerId")
    private String id;
    @SerializedName("sortType")
    private SortType sortType;
    @SerializedName("sortBy")
    private OrderSortBy sortBy;
    public GetAllOrdersCustomerRequest(int page, int limit, String id, SortType sortType, OrderSortBy sortBy) {
        super(page, limit);
        this.id = id;
        this.sortType = sortType;
        this.sortBy = sortBy;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public OrderSortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(OrderSortBy sortBy) {
        this.sortBy = sortBy;
    }
}
