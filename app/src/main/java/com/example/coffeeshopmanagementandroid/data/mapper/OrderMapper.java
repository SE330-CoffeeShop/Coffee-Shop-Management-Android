package com.example.coffeeshopmanagementandroid.data.mapper;

import com.example.coffeeshopmanagementandroid.data.dto.order.response.OrderResponse;
import com.example.coffeeshopmanagementandroid.domain.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static List<OrderModel> mapOrderResponsesToOrdersDomain(List<OrderResponse> orderResponses) {
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderResponse response : orderResponses) {
            OrderModel model = new OrderModel();
            // Chuyển các trường từ OrderResponse sang OrderModel
            model.setOrderId(response.getOrderId());
            model.setUserId(response.getUserId());
            model.setEmployeeId(response.getEmployeeId());
            model.setShippingAddressId(response.getShippingAddressId());
            model.setPaymentMethodId(response.getPaymentMethodId());
            model.setOrderTotalCost(response.getOrderTotalCost());
            model.setOrderDiscountCost(response.getOrderDiscountCost());
            model.setOrderTotalCostAfterDiscount(response.getOrderTotalCostAfterDiscount());
            model.setOrderStatus(response.getOrderStatus());
            model.setOrderTrackingNumber(response.getOrderTrackingNumber());
            orderModels.add(model);
        }
        return orderModels;
    }
}
