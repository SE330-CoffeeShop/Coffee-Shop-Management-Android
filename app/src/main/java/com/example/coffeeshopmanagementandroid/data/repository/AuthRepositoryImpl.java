package com.example.coffeeshopmanagementandroid.data.repository;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.example.coffeeshopmanagementandroid.data.api.AuthService;
import com.example.coffeeshopmanagementandroid.data.dto.BaseResponse;
import com.example.coffeeshopmanagementandroid.data.dto.auth.request.ChangePasswordRequest;
import com.example.coffeeshopmanagementandroid.data.dto.auth.request.LoginRequest;
import com.example.coffeeshopmanagementandroid.data.dto.auth.response.LoginResponse;
import com.example.coffeeshopmanagementandroid.data.dto.auth.response.LogoutResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.AuthMapper;
import com.example.coffeeshopmanagementandroid.domain.model.auth.AuthModel;
import com.example.coffeeshopmanagementandroid.domain.model.auth.UserModel;
import com.example.coffeeshopmanagementandroid.domain.repository.AuthRepository;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Response;


public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService authService;
    private final Context context;

    public AuthRepositoryImpl(
            AuthService authService,
            Context context
    ) {
        this.authService = authService;
        this.context = context;
    }

    @Override
    public Pair<AuthModel, UserModel> login(String email, String password) throws Exception {
        Log.d("AuthRepoImpl", "Login called");

        // Get firebase token
        FirebaseApp.initializeApp(context);

        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<String> tokenRef = new AtomicReference<>();
        final AtomicReference<Exception> exceptionRef = new AtomicReference<>();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Firebase token retrieved successfully");
                        tokenRef.set(task.getResult());
                    } else {
                        Log.w(TAG, "Failed to get FCM token", task.getException());
                        exceptionRef.set(task.getException());
                    }
                    latch.countDown(); // Signal completion
                });

        // Wait for the token with a timeout
        String firebaseToken = null;
        try {
            if (latch.await(10, TimeUnit.SECONDS)) {
                firebaseToken = tokenRef.get();
                Log.d(TAG, "Firebase token: " + (firebaseToken != null ? "obtained" : "null"));
            } else {
                Log.e(TAG, "Timeout waiting for Firebase token");
            }
        } catch (InterruptedException e) {
            Log.e(TAG, "Interrupted while waiting for Firebase token", e);
            Thread.currentThread().interrupt();
        }

        Call<BaseResponse<LoginResponse>> call = authService.login(new LoginRequest(email, password, firebaseToken));
        Response<BaseResponse<LoginResponse>> response = call.execute();

        Log.d("LOGIN", "Response received: " + response);

        if (response.isSuccessful() && response.body() != null) {
            BaseResponse<LoginResponse> baseResponse = response.body();
            LoginResponse data = baseResponse.getData();

            if (baseResponse.getData() != null) {
                Log.d("Response", "Login ID: " + data.getId());
                UserModel user = AuthMapper.mapLoginResponseToUserDomain(data);
                AuthModel authModel = AuthMapper.mapLoginResponseToAuthDomain(data);
                return new Pair<>(authModel, user);
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

    @Override
    public Void changePassword(ChangePasswordRequest request) throws Exception {
        Log.d("AuthRepoImpl", "Change password called");
        Call<Void> call = authService.changePassword(request);
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {
            Log.d("Response", "Password changed successfully");
            return null;
        } else {
            String errorMessage = "Change password failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("CHANGE_PASSWORD", errorMessage);
            throw new Exception(errorMessage);
        }
    }
}
