package com.example.coffeeshopmanagementandroid.data.dto.auth;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;
    @SerializedName("rememberMe")
    private Boolean rememberMe;

    public LoginRequest(String email, String password, Boolean rememberMe) {
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe;
    }
}