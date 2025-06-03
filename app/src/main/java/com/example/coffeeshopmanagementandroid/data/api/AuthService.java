package com.example.coffeeshopmanagementandroid.data.api;


import com.example.coffeeshopmanagementandroid.data.dto.auth.LoginRequest;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LoginResponse;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LogoutRequest;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LogoutResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("auth/logout")
    Call<LogoutResponse> logout();
}
