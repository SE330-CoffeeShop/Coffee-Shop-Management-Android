package com.example.coffeeshopmanagementandroid.di;

import android.content.Context;

import com.example.coffeeshopmanagementandroid.data.api.AuthService;
import com.example.coffeeshopmanagementandroid.data.api.CategoryService;
import com.example.coffeeshopmanagementandroid.data.api.FavoriteProductService;
import com.example.coffeeshopmanagementandroid.data.api.OrderService;
import com.example.coffeeshopmanagementandroid.data.api.ProductService;
import com.example.coffeeshopmanagementandroid.data.api.UserService;
import com.example.coffeeshopmanagementandroid.data.repository.AuthRepositoryImpl;
import com.example.coffeeshopmanagementandroid.data.repository.CategoryRepositoryImpl;
import com.example.coffeeshopmanagementandroid.data.repository.FavoriteProductRepositoryImpl;
import com.example.coffeeshopmanagementandroid.data.repository.OrderRepositoryImpl;
import com.example.coffeeshopmanagementandroid.data.repository.ProductRepositoryImpl;
import com.example.coffeeshopmanagementandroid.data.repository.UserRepositoryImpl;
import com.example.coffeeshopmanagementandroid.domain.repository.AuthRepository;
import com.example.coffeeshopmanagementandroid.domain.repository.CategoryRepository;
import com.example.coffeeshopmanagementandroid.domain.repository.FavoriteProductRepository;
import com.example.coffeeshopmanagementandroid.domain.repository.OrderRepository;
import com.example.coffeeshopmanagementandroid.domain.repository.ProductRepository;
import com.example.coffeeshopmanagementandroid.domain.repository.UserRepository;
import com.example.coffeeshopmanagementandroid.domain.usecase.CategoryUseCase;
import com.example.coffeeshopmanagementandroid.domain.usecase.FavoriteProductUseCase;
import com.example.coffeeshopmanagementandroid.domain.usecase.LoginUseCase;
import com.example.coffeeshopmanagementandroid.domain.usecase.LogoutUseCase;
import com.example.coffeeshopmanagementandroid.domain.usecase.OrderUseCase;
import com.example.coffeeshopmanagementandroid.domain.usecase.ProductUseCase;
import com.example.coffeeshopmanagementandroid.domain.usecase.UserUseCase;
import com.example.coffeeshopmanagementandroid.utils.RetrofitInstance;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static RetrofitInstance provideRetrofitInstance(@ApplicationContext Context context) {
        return new RetrofitInstance(context);
    }

    @Provides
    @Singleton
    public static AuthService provideAuthService(RetrofitInstance retrofitInstance) {
        return retrofitInstance.createService(AuthService.class);
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

    @Provides
    public static LogoutUseCase provideLogoutUseCase(AuthRepository authRepository) {
        return new LogoutUseCase(authRepository);
    }

    @Provides
    @Singleton
    public static ProductService provideProductService(RetrofitInstance retrofitInstance) {
        return retrofitInstance.createService(ProductService.class);
    }

    @Provides
    @Singleton
    public static ProductRepository provideProductRepository(ProductService productService) {
        return new ProductRepositoryImpl(productService);
    }

    @Provides
    public static ProductUseCase provideProductUseCase(ProductRepository productRepository) {
        return new ProductUseCase(productRepository);
    }
    @Provides
    @Singleton
    public static CategoryService provideCategoryService(RetrofitInstance retrofitInstance) {
        return retrofitInstance.createService(CategoryService.class);
    }
    @Provides
    @Singleton
    public static CategoryRepository provideCategoryRepository(CategoryService categoryService) {
        return new CategoryRepositoryImpl(categoryService);
    }
    @Provides
    public static CategoryUseCase provideCategoryUseCase(CategoryRepository categoryRepository) {
        return new CategoryUseCase(categoryRepository);
    }
    @Provides
    @Singleton
    public static OrderService provideOrderService(RetrofitInstance retrofitInstance) {
        return retrofitInstance.createService(OrderService.class);
    }
    @Provides
    @Singleton
    public static OrderRepository provideOrderRepository(OrderService orderService) {
        return new OrderRepositoryImpl(orderService);
    }
    @Provides
    public static OrderUseCase provideOrderUseCase(OrderRepository orderRepository) {
        return new OrderUseCase(orderRepository);
    }
    @Provides
    @Singleton
    public static FavoriteProductService provideFavoriteProductService(RetrofitInstance retrofitInstance) {
        return retrofitInstance.createService(FavoriteProductService.class);
    }
    @Provides
    @Singleton
    public static FavoriteProductRepository provideFavoriteProductRepository(FavoriteProductService favoriteProductService) {
        return new FavoriteProductRepositoryImpl(favoriteProductService);
    }
    @Provides
    public static FavoriteProductUseCase provideFavoriteProductUseCase(FavoriteProductRepository favoriteProductRepository) {
        return new FavoriteProductUseCase(favoriteProductRepository);
    }
    @Provides
    @Singleton
    public static UserService provideUserService(RetrofitInstance retrofitInstance) {
        return retrofitInstance.createService(UserService.class);
    }
    @Provides
    @Singleton
    public static UserRepository provideUserRepository(UserService userService) {
        return new UserRepositoryImpl(userService);
    }
    @Provides
    public static UserUseCase provideUserUseCase(UserRepository userRepository) {
        return new UserUseCase(userRepository);
    }
}
