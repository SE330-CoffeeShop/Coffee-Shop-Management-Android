package com.example.coffeeshopmanagementandroid.data.repository;
import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.api.AuthService;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LoginRequest;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LoginResponse;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LogoutRequest;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LogoutResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.AuthMapper;
import com.example.coffeeshopmanagementandroid.domain.model.AuthModel;
import com.example.coffeeshopmanagementandroid.domain.repository.AuthRepository;

import retrofit2.Call;
import retrofit2.Response;


public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService authService;

    public AuthRepositoryImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public AuthModel login(String email, String password, Boolean rememberMe) throws Exception {
        Log.d("Auth RepoIml", "Called");
        Call<LoginResponse> call = authService.login(new LoginRequest(email, password, rememberMe));
        Response<LoginResponse> response = call.execute();

        Log.d("LOGIN", "Response received: " + response.toString()); // Log response trước khi xử lý

        if (response.isSuccessful() && response.body() != null) {
            Log.d("Response", String.valueOf(response.body().getToken()));
            return AuthMapper.mapToDomain(response.body());
        } else {
            String errorMessage = "Login failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("LOGIN", errorMessage); // Log lỗi trước khi ném Exception
            throw new Exception(errorMessage);
        }
    }

    @Override
    public String logout() throws Exception {
        Log.d("Auth RepoIml", "Called");
        Call<LogoutResponse> call = authService.logout();
        Response<LogoutResponse> response = call.execute();

        if (response.isSuccessful() && response.body() != null) {
            Log.d("Response", String.valueOf(response.body().getMessage()));
            return response.body().toString();
        } else {
            String errorMessage = "Logout failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("LOGOUT", errorMessage); // Log lỗi trước khi ném Exception
            throw new Exception(errorMessage);
        }
    }

}
