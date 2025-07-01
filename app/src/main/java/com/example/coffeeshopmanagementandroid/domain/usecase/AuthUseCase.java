package com.example.coffeeshopmanagementandroid.domain.usecase;

import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.dto.auth.request.ChangePasswordRequest;
import com.example.coffeeshopmanagementandroid.domain.repository.AuthRepository;
import com.example.coffeeshopmanagementandroid.domain.repository.ProductRepository;

public class AuthUseCase {
    private final AuthRepository authRepository;

    public AuthUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void changePassword(ChangePasswordRequest request) throws Exception {
        authRepository.changePassword(request);
    }

}
