package com.example.coffeeshopmanagementandroid.domain.model;

public class OrderItemModel {
    private String thumb;
    private String nameVariant;
    private int quantity;
    private double unitPrice;

    public OrderItemModel(String thumb, String nameVariant, int quantity, double unitPrice) {
        this.thumb = thumb;
        this.nameVariant = nameVariant;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getThumb() { return thumb; }
    public void setThumb(String thumb) { this.thumb = thumb; }

    public String getNameVariant() { return nameVariant; }
    public void setNameVariant(String nameVariant) { this.nameVariant = nameVariant; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}
