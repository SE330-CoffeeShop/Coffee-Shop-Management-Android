package com.example.coffeeshopmanagementandroid.data.dto;

public class BaseResponse<T> {
    private T data;
    private String message;
    public BaseResponse() {
    }

    public BaseResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
