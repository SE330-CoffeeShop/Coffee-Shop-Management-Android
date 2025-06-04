package com.example.coffeeshopmanagementandroid.data.dto.auth.response;

import com.example.coffeeshopmanagementandroid.utils.enums.Role;

public class LoginResponse extends TokenResponse {
    private String userId;

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
    }

}
