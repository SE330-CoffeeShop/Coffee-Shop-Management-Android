package com.example.coffeeshopmanagementandroid.domain.model;

import java.sql.Timestamp;

public class DiscountModel {
    private String discountId;
    private String discountName;
    private String discountDescription;
    private String discountType;
    private Double discountValue;
    private String discountCode;
    private Timestamp discountStartDate;
    private Timestamp discountEndDate;
    private int discountMaxUses;
    private int discountUserCount;
    private int discountMaxPerUser;
    private int discountMinOrderValue;
    private Boolean discountIsActive;
    private String discountBranchId;

    public DiscountModel(
            String discountId,
            String discountName,
            String discountDescription,
            String discountType,
            Double discountValue,
            String discountCode,
            Timestamp discountStartDate,
            Timestamp discountEndDate,
            int discountMaxUses,
            int discountUserCount,
            int discountMaxPerUser,
            int discountMinOrderValue,
            Boolean discountIsActive,
            String discountBranchId
    ) {
        this.discountId = discountId;
        this.discountName = discountName;
        this.discountDescription = discountDescription;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.discountCode = discountCode;
        this.discountStartDate = discountStartDate;
        this.discountEndDate = discountEndDate;
        this.discountMaxUses = discountMaxUses;
        this.discountUserCount = discountUserCount;
        this.discountMaxPerUser = discountMaxPerUser;
        this.discountMinOrderValue = discountMinOrderValue;
        this.discountIsActive = discountIsActive;
        this.discountBranchId = discountBranchId;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Timestamp getDiscountStartDate() {
        return discountStartDate;
    }

    public void setDiscountStartDate(Timestamp discountStartDate) {
        this.discountStartDate = discountStartDate;
    }

    public Timestamp getDiscountEndDate() {
        return discountEndDate;
    }

    public void setDiscountEndDate(Timestamp discountEndDate) {
        this.discountEndDate = discountEndDate;
    }

    public int getDiscountMaxUses() {
        return discountMaxUses;
    }

    public void setDiscountMaxUses(int discountMaxUses) {
        this.discountMaxUses = discountMaxUses;
    }

    public int getDiscountUserCount() {
        return discountUserCount;
    }

    public void setDiscountUserCount(int discountUserCount) {
        this.discountUserCount = discountUserCount;
    }

    public int getDiscountMaxPerUser() {
        return discountMaxPerUser;
    }

    public void setDiscountMaxPerUser(int discountMaxPerUser) {
        this.discountMaxPerUser = discountMaxPerUser;
    }

    public int getDiscountMinOrderValue() {
        return discountMinOrderValue;
    }

    public void setDiscountMinOrderValue(int discountMinOrderValue) {
        this.discountMinOrderValue = discountMinOrderValue;
    }

    public Boolean getDiscountIsActive() {
        return discountIsActive;
    }

    public void setDiscountIsActive(Boolean discountIsActive) {
        this.discountIsActive = discountIsActive;
    }

    public String getDiscountBranchId() {
        return discountBranchId;
    }

    public void setDiscountBranchId(String discountBranchId) {
        this.discountBranchId = discountBranchId;
    }
}