package com.example.coffeeshopmanagementandroid.data.dto.auth.request;

public class ChangePasswordRequest {
    private String oldPassword;
    private String password;
    private String passwordConfirm;

    public ChangePasswordRequest(String oldPassword, String password, String passwordConfirm) {
        this.oldPassword = oldPassword;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
