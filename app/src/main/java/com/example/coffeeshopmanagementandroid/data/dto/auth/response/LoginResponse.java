package com.example.coffeeshopmanagementandroid.data.dto.auth.response;

import com.example.coffeeshopmanagementandroid.utils.enums.Role;

public class LoginResponse extends TokenResponse {
    private String id;
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
