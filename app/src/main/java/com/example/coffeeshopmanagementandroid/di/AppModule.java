package com.example.coffeeshopmanagementandroid.di;

import android.content.Context;

import com.example.coffeeshopmanagementandroid.data.api.AuthService;
import com.example.coffeeshopmanagementandroid.data.repository.AuthRepositoryImpl;
import com.example.coffeeshopmanagementandroid.domain.repository.AuthRepository;
import com.example.coffeeshopmanagementandroid.domain.usecase.LoginUseCase;
import com.example.coffeeshopmanagementandroid.utils.RetrofitInstance;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static AuthService provideAuthService() {
        return RetrofitInstance.getInstance().create(AuthService.class);
    }

    @Provides
    @Singleton
    public static AuthRepository provideAuthRepository(AuthService authService) {
        return new AuthRepositoryImpl(authService);
    }

    @Provides
    public static LoginUseCase provideLoginUseCase(AuthRepository authRepository) {
        return new LoginUseCase(authRepository);
    }
}
