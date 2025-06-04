package com.example.coffeeshopmanagementandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final Context context;

    public AuthInterceptor(Context context) {
        this.context = context.getApplicationContext(); // Sử dụng Application Context để tránh memory leak
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        // Lấy token từ SharedPreferences
        SharedPreferences prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = prefs.getString("auth_token", null);

        // Lấy request hiện tại
        Request request = chain.request();

        // Nếu có token, thêm vào header Authorization
        if (token != null) {
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
        }  else {
            return chain.proceed(request); // Nếu không có token, không thêm Authorization
        }

        // Tiếp tục gửi request
        return chain.proceed(request);
    }
}