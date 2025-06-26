package com.example.coffeeshopmanagementandroid.data.dto.address.resquest;

public class UpdateAddressRequest {
    private String addressLine;
    private String addressCity;
    private String addressDistrict;
    private Boolean addressIsDefault;
    private String shippingAddressId;

    public UpdateAddressRequest(String addressLine, String addressCity, String addressDistrict, Boolean addressIsDefault, String shippingAddressId) {
        this.addressLine = addressLine;
        this.addressCity = addressCity;
        this.addressDistrict = addressDistrict;
        this.addressIsDefault = addressIsDefault;
        this.shippingAddressId = shippingAddressId;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public Boolean getAddressIsDefault() {
        return addressIsDefault;
    }

    public void setAddressIsDefault(Boolean addressIsDefault) {
        this.addressIsDefault = addressIsDefault;
    }

    public String getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }
}
