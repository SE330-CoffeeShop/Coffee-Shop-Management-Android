package com.example.coffeeshopmanagementandroid.data.dto.order.response;

public class OrderResponse {
    private String orderId;
    private String userId;
    private String employeeId;
    private String shippingAddressId;
    private String paymentMethodId;
    private int orderTotalCost;
    private int orderDiscountCost;
    private int orderTotalCostAfterDiscount;
    private String orderStatus;
    private String orderTrackingNumber;

    public OrderResponse(String orderId, String userId, String employeeId, String shippingAddressId, String paymentMethodId, int orderTotalCost, int orderDiscountCost, int orderTotalCostAfterDiscount, String orderStatus, String orderTrackingNumber) {
        this.orderId = orderId;
        this.userId = userId;
        this.employeeId = employeeId;
        this.shippingAddressId = shippingAddressId;
        this.paymentMethodId = paymentMethodId;
        this.orderTotalCost = orderTotalCost;
        this.orderDiscountCost = orderDiscountCost;
        this.orderTotalCostAfterDiscount = orderTotalCostAfterDiscount;
        this.orderStatus = orderStatus;
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public int getOrderTotalCost() {
        return orderTotalCost;
    }

    public void setOrderTotalCost(int orderTotalCost) {
        this.orderTotalCost = orderTotalCost;
    }

    public int getOrderDiscountCost() {
        return orderDiscountCost;
    }

    public void setOrderDiscountCost(int orderDiscountCost) {
        this.orderDiscountCost = orderDiscountCost;
    }

    public int getOrderTotalCostAfterDiscount() {
        return orderTotalCostAfterDiscount;
    }

    public void setOrderTotalCostAfterDiscount(int orderTotalCostAfterDiscount) {
        this.orderTotalCostAfterDiscount = orderTotalCostAfterDiscount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

}
