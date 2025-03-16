package com.example.coffeeshopmanagementandroid.data.mapper;

import com.example.coffeeshopmanagementandroid.data.dto.auth.LoginResponse;
import com.example.coffeeshopmanagementandroid.domain.model.AuthModel;

public class AuthMapper {
    public static AuthModel mapToDomain(LoginResponse response) {
        return new AuthModel(
                response.getToken(),
                response.getRefreshToken(),
                response.getExpiresIn().getToken(),
                response.getExpiresIn().getRefreshToken()
        );
    }
}
