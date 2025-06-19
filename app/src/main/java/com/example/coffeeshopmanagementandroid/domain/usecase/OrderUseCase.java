package com.example.coffeeshopmanagementandroid.domain.usecase;

import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.order.request.CreateOrderRequest;
import com.example.coffeeshopmanagementandroid.data.dto.order.request.GetAllOrdersCustomerRequest;
import com.example.coffeeshopmanagementandroid.data.dto.order.response.OrderResponse;
import com.example.coffeeshopmanagementandroid.domain.model.OrderModel;
import com.example.coffeeshopmanagementandroid.domain.repository.OrderRepository;

import java.util.List;

public class OrderUseCase {
    private final OrderRepository orderRepository;

    public OrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public BasePagingResponse<List<OrderResponse>> getAllOrdersCustomer(GetAllOrdersCustomerRequest request) throws Exception {
        Log.d("Order Use Case", "Called");
        return orderRepository.getAllOrdersCustomer(request);
    }

    public Void createOrder(CreateOrderRequest request) throws Exception {
        Log.d("Order Use Case - createOrder", "Called");
        try {
            return orderRepository.createOrder(request);
        } catch (Exception e) {
            Log.e("Order Use Case - createOrder", "Error creating order: " + e.getMessage());
            throw new Exception("Failed to create order", e);
        }

    }
}
