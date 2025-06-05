package com.example.coffeeshopmanagementandroid.data.dto.auth.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email")
    private final String email;
    @SerializedName("password")
    private final String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}