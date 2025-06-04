package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.product.request.GetAllProductsRequest;
import com.example.coffeeshopmanagementandroid.data.dto.product.response.ProductResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.ProductMapper;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.domain.usecase.ProductUseCase;
import com.example.coffeeshopmanagementandroid.utils.enums.ProductSortBy;
import com.example.coffeeshopmanagementandroid.utils.enums.SortType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductViewModel extends ViewModel {
    private final ProductUseCase productUseCase;
    private final MutableLiveData<List<ProductModel>> productsResult = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Integer> page = new MutableLiveData<>(1);
    private final MutableLiveData<Integer> limit = new MutableLiveData<>(10);
    private final MutableLiveData<Integer> total = new MutableLiveData<>(null);
    private final MutableLiveData<List<ProductModel>> recentlyProductsResult = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isAllDataLoaded = new MutableLiveData<>(false); // Thêm cờ này

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

    public LiveData<Boolean> getIsAllDataLoaded() { // Thêm getter
        return isAllDataLoaded;
    }

    public void setProductsResult(List<ProductModel> products) {
        productsResult.postValue(products);
    }

    public void setRecentlyProductsResult(List<ProductModel> recentlyProducts) {
        recentlyProductsResult.postValue(recentlyProducts);
    }

    public void setErrorLiveData(String errorMessage) {
        errorLiveData.postValue(errorMessage);
    }

    public void setIsLoading(Boolean loading) {
        isLoading.postValue(loading);
    }

    // Fetch initial products
    public void fetchAllProducts(int page, int limit, SortType sortType, ProductSortBy sortBy) {
        setIsLoading(true);
        new Thread(() -> {
            try {
                GetAllProductsRequest request = new GetAllProductsRequest(page, limit, sortType, sortBy);
                BasePagingResponse<List<ProductResponse>> result = productUseCase.getAllProducts(request);
                if (result != null && result.getData() != null) {
                    setTotal(result.getPaging().getTotal());
                    List<ProductModel> newProducts = ProductMapper.mapProductResponsesToProductsDomain(result.getData());
                    appendProducts(newProducts);
                }
            } catch (Exception e) {
                setErrorLiveData(e.getMessage());
                Log.e("ProductViewModel", "Fetch all products failed: " + e.getMessage(), e);
            } finally {
                setIsLoading(false);
            }
        }).start();
    }

    // Fetch more products for infinite scroll
    public void fetchMoreProducts(SortType sortType, ProductSortBy sortBy) {
        if (isAllDataLoaded.getValue() != null && isAllDataLoaded.getValue()) {
            Log.d("ProductViewModel", "All data already loaded, skipping fetch");
            return;
        }
        int currentPage = page.getValue() != null ? page.getValue() : 1;
        int nextPage = currentPage + 1;
        setPage(nextPage);
        fetchAllProducts(nextPage, limit.getValue(), sortType, sortBy);

        // Cập nhật isAllDataLoaded khi đã tải đủ
        if (productsResult.getValue() != null && total.getValue() != null && productsResult.getValue().size() >= total.getValue()) {
            isAllDataLoaded.postValue(true);
        }
    }

    // Phương thức append dữ liệu mới vào danh sách hiện tại
    private void appendProducts(List<ProductModel> newProducts) {
        List<ProductModel> currentProducts = productsResult.getValue() != null ? new ArrayList<>(productsResult.getValue()) : new ArrayList<>();
        for (ProductModel newProduct : newProducts) {
            if (!currentProducts.contains(newProduct)) {
                currentProducts.add(newProduct);
            }
        }
        productsResult.postValue(currentProducts);
    }

    private void setPage(int page) {
        this.page.postValue(page);
    }

    private void setTotal(int total) {
        this.total.postValue(total);
    }
}