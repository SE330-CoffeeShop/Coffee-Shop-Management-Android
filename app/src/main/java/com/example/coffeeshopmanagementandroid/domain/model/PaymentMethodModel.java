package com.example.coffeeshopmanagementandroid.domain.model;

public class PaymentMethodModel {
    private String paymentMethodId;
    private String paymentMethodType;
    private String paymentMethodDetail;
    private boolean paymentMethodIsDefault;
    private String paymentMethodUserId;

    // Constructor không tham số
    public PaymentMethodModel() {
    }

    // Constructor có tham số
    public PaymentMethodModel(String paymentMethodId, String paymentMethodType, String paymentMethodDetail,
                              boolean paymentMethodIsDefault, String paymentMethodUserId) {
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodType = paymentMethodType;
        this.paymentMethodDetail = paymentMethodDetail;
        this.paymentMethodIsDefault = paymentMethodIsDefault;
        this.paymentMethodUserId = paymentMethodUserId;
    }

    // Getter và Setter cho paymentMethodId
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    // Getter và Setter cho paymentMethodType
    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    // Getter và Setter cho paymentMethodDetail
    public String getPaymentMethodDetail() {
        return paymentMethodDetail;
    }

    public void setPaymentMethodDetail(String paymentMethodDetail) {
        this.paymentMethodDetail = paymentMethodDetail;
    }

    // Getter và Setter cho paymentMethodIsDefault
    public boolean isPaymentMethodIsDefault() {
        return paymentMethodIsDefault;
    }

    public void setPaymentMethodIsDefault(boolean paymentMethodIsDefault) {
        this.paymentMethodIsDefault = paymentMethodIsDefault;
    }

    // Getter và Setter cho paymentMethodUserId
    public String getPaymentMethodUserId() {
        return paymentMethodUserId;
    }

    public void setPaymentMethodUserId(String paymentMethodUserId) {
        this.paymentMethodUserId = paymentMethodUserId;
    }
}
