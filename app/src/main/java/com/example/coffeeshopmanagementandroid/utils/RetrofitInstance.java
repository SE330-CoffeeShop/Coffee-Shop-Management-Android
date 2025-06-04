package com.example.coffeeshopmanagementandroid.utils;

import android.content.Context;
import com.example.coffeeshopmanagementandroid.BuildConfig;

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
        OkHttpClient client = null;
        if (retrofit == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(context))
                    .authenticator(new TokenAuthenticator(context)) // <-- Add this line
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public <T> T createService(Class<T> serviceClass) {
        return getRetrofit().create(serviceClass);
    }
}