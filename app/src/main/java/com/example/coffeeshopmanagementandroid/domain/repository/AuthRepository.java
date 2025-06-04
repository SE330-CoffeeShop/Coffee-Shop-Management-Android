package com.example.coffeeshopmanagementandroid.domain.repository;

import com.example.coffeeshopmanagementandroid.data.dto.auth.response.LoginResponse;
import com.example.coffeeshopmanagementandroid.domain.model.auth.AuthModel;

public interface AuthRepository {
    AuthModel login(String email, String password) throws Exception;

    String logout() throws Exception;
}