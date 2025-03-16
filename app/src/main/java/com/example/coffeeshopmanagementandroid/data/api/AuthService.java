package com.example.coffeeshopmanagementandroid.data.api;


import com.example.coffeeshopmanagementandroid.data.dto.auth.LoginRequest;
import com.example.coffeeshopmanagementandroid.data.dto.auth.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
