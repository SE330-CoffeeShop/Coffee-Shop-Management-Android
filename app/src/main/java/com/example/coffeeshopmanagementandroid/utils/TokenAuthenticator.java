package com.example.coffeeshopmanagementandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.example.coffeeshopmanagementandroid.BuildConfig;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {
    private final Context context;

    public TokenAuthenticator(Context context) {
        this.context = context.getApplicationContext();
    }

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        // Prevent infinite loops
        if (responseCount(response) >= 2) return null;

        // Get refresh token
        SharedPreferences prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String refreshToken = prefs.getString("refresh_token", null);

        if (refreshToken == null) return null;

        // Call refresh endpoint (sync call using Retrofit or OkHttp directly)
        String newAccessToken = refreshAccessToken(refreshToken);
        if (newAccessToken == null) return null;

        // Save new token
        prefs.edit().putString("access_token", newAccessToken).apply();

        // Retry the original request with new token
        return response.request().newBuilder()
                .header("Authorization", "Bearer " + newAccessToken)
                .build();
    }

    private int responseCount(Response response) {
        int count = 1;
        while ((response = response.priorResponse()) != null) count++;
        return count;
    }

    private String refreshAccessToken(String refreshToken) {
        // TODO: implement actual refresh token call
        // Example using OkHttp for sync call
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(BuildConfig.BASE_URL + "auth/refresh") // Change to your actual refresh endpoint
                .post(okhttp3.RequestBody.create(null, new byte[0])) // Or form body if needed
                .header("Authorization", "Bearer " + refreshToken)
                .build();

        try (okhttp3.Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Parse response JSON to get access token
                String responseBody = response.body().string();
                // You can use a JSON library here
                // Assuming response is like: { "access_token": "..." }
                return parseAccessToken(responseBody);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseAccessToken(String responseBody) {
        // TODO: Use proper JSON parsing, e.g., Gson or JSONObject
        try {
            org.json.JSONObject json = new org.json.JSONObject(responseBody);
            return json.getString("access_token");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
