package com.example.coffeeshopmanagementandroid.utils;

import android.content.Context;

import com.example.coffeeshopmanagementandroid.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = BuildConfig.BASE_URL;
    private final Context context;

    public RetrofitInstance(Context context) {
        this.context = context.getApplicationContext();
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(context))
                    .authenticator(new TokenAuthenticator(context))
                    .build();

            // Sử dụng định dạng ngày tương thích với server (ISO 8601 không timezone)
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson)) // Dùng Gson custom
                    .build();
        }
        return retrofit;
    }

    public <T> T createService(Class<T> serviceClass) {
        return getRetrofit().create(serviceClass);
    }
}
