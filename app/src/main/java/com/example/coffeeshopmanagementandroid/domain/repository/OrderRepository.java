package com.example.coffeeshopmanagementandroid.domain.repository;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.order.request.CreateOrderRequest;
import com.example.coffeeshopmanagementandroid.data.dto.order.request.GetAllOrdersCustomerRequest;
import com.example.coffeeshopmanagementandroid.data.dto.order.response.OrderResponse;

import java.util.List;

public interface OrderRepository {
    BasePagingResponse<List<OrderResponse>> getAllOrdersCustomer(GetAllOrdersCustomerRequest request) throws Exception;
    Void createOrder(CreateOrderRequest request) throws Exception;
}
