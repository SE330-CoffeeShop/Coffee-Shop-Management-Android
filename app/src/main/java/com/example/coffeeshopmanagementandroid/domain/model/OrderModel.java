package com.example.coffeeshopmanagementandroid.domain.model;

import java.util.List;

public class OrderModel {
    private String orderId;
    private String orderStatus;
    private List<OrderItemModel> items;
    private double totalOrder;

    public OrderModel(String orderId, String orderStatus, List<OrderItemModel> items) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.items = items;
        this.totalOrder = this.items.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public List<OrderItemModel> getItems() { return items; }
    public void setItems(List<OrderItemModel> items) { this.items = items; }
    public double getTotalOrder() { return totalOrder; }
    public void setTotalOrder(double totalOrder) { this.totalOrder = totalOrder; }
}
