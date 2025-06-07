package com.example.coffeeshopmanagementandroid.utils.enums;

import androidx.annotation.NonNull;

public enum OrderStatus {
    PENDING("Chờ tiếp nhận"),
    PROCESSING("Đang chuẩn bị"),
    DELIVERING("Đang giao hàng"),
    COMPLETED("Hoàn thành"),
    CANCELLED("Đã huỷ");
    private final String status;
    OrderStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    @NonNull
    @Override
    public String toString() {
        return status;
    }
}
