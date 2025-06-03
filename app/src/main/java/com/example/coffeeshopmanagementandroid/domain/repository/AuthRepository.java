package com.example.coffeeshopmanagementandroid.domain.repository;

import com.example.coffeeshopmanagementandroid.domain.model.AuthModel;

public interface AuthRepository {
    AuthModel login(String email, String password, Boolean rememberMe) throws Exception;

    String logout() throws Exception;
}