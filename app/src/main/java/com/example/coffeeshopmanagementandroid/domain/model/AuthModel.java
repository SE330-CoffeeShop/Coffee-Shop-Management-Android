package com.example.coffeeshopmanagementandroid.domain.model;

public class AuthModel {
    private String token;
    private String refreshToken;
    private long tokenExpiresIn;
    private long refreshTokenExpiresIn;

    public AuthModel(String token, String refreshToken, long tokenExpiresIn, long refreshTokenExpiresIn) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.tokenExpiresIn = tokenExpiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getTokenExpiresIn() {
        return tokenExpiresIn;
    }

    public long getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }
}
