package com.example.coffeeshopmanagementandroid.data.repository;
import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.api.AuthService;
import com.example.coffeeshopmanagementandroid.data.dto.BaseResponse;
import com.example.coffeeshopmanagementandroid.data.dto.auth.request.LoginRequest;
import com.example.coffeeshopmanagementandroid.data.dto.auth.response.LoginResponse;
import com.example.coffeeshopmanagementandroid.data.dto.auth.response.LogoutResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.AuthMapper;
import com.example.coffeeshopmanagementandroid.domain.model.auth.AuthModel;
import com.example.coffeeshopmanagementandroid.domain.repository.AuthRepository;

import retrofit2.Call;
import retrofit2.Response;


public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService authService;

    public AuthRepositoryImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public AuthModel login(String email, String password) throws Exception {
        Log.d("AuthRepoImpl", "Login called");
        Call<BaseResponse<LoginResponse>> call = authService.login(new LoginRequest(email, password));
        Response<BaseResponse<LoginResponse>> response = call.execute();

        Log.d("LOGIN", "Response received: " + response.toString());

        if (response.isSuccessful() && response.body() != null) {
            BaseResponse<LoginResponse> baseResponse = response.body();
            LoginResponse data = baseResponse.getData();

            if (baseResponse.getData() != null) {
                Log.d("Response", "Login ID: " + data.getId());
                return AuthMapper.mapLoginResponseToAuthDomain(data);
            } else {
                throw new Exception("Login failed: empty data");
            }
        } else {
            String errorMessage = "Login failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("LOGIN", errorMessage);
            throw new Exception(errorMessage);
        }
    }

    @Override
    public String logout() throws Exception {
        Log.d("AuthRepoImpl", "Logout called");
        Call<BaseResponse<LogoutResponse>> call = authService.logout();
        Response<BaseResponse<LogoutResponse>> response = call.execute();

        if (response.isSuccessful() && response.body() != null) {
            BaseResponse<LogoutResponse> baseResponse = response.body();

            if (baseResponse.getData() != null) {
                Log.d("Response", "Logout message: " + baseResponse.getMessage());
                return baseResponse.getMessage();
            } else {
                throw new Exception("Logout failed: empty data");
            }
        } else {
            String errorMessage = "Logout failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("LOGOUT", errorMessage);
            throw new Exception(errorMessage);
        }
    }

}
