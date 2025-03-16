package com.example.coffeeshopmanagementandroid.data.repository;
import com.example.coffeeshopmanagementandroid.data.api.AuthService;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LoginRequest;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LoginResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.AuthMapper;
import com.example.coffeeshopmanagementandroid.domain.model.AuthModel;
import com.example.coffeeshopmanagementandroid.domain.repository.AuthRepository;
import com.example.coffeeshopmanagementandroid.utils.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService authService;

    public AuthRepositoryImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public AuthModel login(String email, String password, Boolean rememberMe) throws Exception {
        Call<LoginResponse> call = authService.login(new LoginRequest(email, password, rememberMe));
        Response<LoginResponse> response = call.execute();

        if (response.isSuccessful() && response.body() != null) {
            return AuthMapper.mapToDomain(response.body());
        } else {
            throw new Exception("Login failed: " + response.errorBody().string());
        }
    }
}
