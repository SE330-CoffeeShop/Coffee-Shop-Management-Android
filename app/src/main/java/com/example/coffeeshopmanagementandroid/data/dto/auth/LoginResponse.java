package com.example.coffeeshopmanagementandroid.data.dto.auth;

public class LoginResponse {
    private String token;
    private String refreshToken;
    private ExpiresIn expiresIn;

    public static class ExpiresIn {
        private long token;
        private long refreshToken;

        public long getToken() {
            return token;
        }

        public long getRefreshToken() {
            return refreshToken;
        }
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public ExpiresIn getExpiresIn() {
        return expiresIn;
    }
}
