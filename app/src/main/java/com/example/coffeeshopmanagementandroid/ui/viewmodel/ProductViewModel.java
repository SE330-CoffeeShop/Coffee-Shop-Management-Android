package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.domain.usecase.ProductUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductViewModel extends ViewModel {
    private final ProductUseCase productUseCase;
    private final MutableLiveData<List<ProductModel>> productsResult = new MutableLiveData<>();
    private final MutableLiveData<List<ProductModel>> recentlyProductsResult = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    @Inject
    public ProductViewModel(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    public LiveData<List<ProductModel>> getProductsResult() {
        return productsResult;
    }

    public LiveData<List<ProductModel>> getRecentlyProductsResult() {
        return recentlyProductsResult;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setProductsResult(List<ProductModel> products) {
        productsResult.setValue(products);
    }

    public void setRecentlyProductsResult(List<ProductModel> recentlyProducts) {
        recentlyProductsResult.setValue(recentlyProducts);
    }

    public void setErrorLiveData(String errorMessage) {
        errorLiveData.setValue(errorMessage);
    }

    public void setIsLoading(Boolean loading) {
        isLoading.setValue(loading);
    }

    public void fetchAllProducts(int page, int limit, String sortType, String sortBy) {
        isLoading.setValue(true);
        new Thread(() -> {
            try {
                List<ProductModel> result = productUseCase.getAllProducts(page, limit, sortType, sortBy);
                productsResult.postValue(result);
            } catch (Exception e) {
                errorLiveData.postValue(e.getMessage());
                Log.e("ProductViewModel", "Fetch all products failed: " + e.getMessage(), e);
            } finally {
                isLoading.postValue(false);
            }
        }).start();
    }

}
