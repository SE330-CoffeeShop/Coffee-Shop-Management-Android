package com.example.coffeeshopmanagementandroid.domain.usecase;

import com.example.coffeeshopmanagementandroid.domain.model.AuthModel;
import com.example.coffeeshopmanagementandroid.domain.repository.AuthRepository;

public class LoginUseCase {
    private final AuthRepository authRepository;

    public LoginUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public AuthModel execute(String email, String password, Boolean rememberMe) throws Exception {
        return authRepository.login(email, password, rememberMe);
    }
}
