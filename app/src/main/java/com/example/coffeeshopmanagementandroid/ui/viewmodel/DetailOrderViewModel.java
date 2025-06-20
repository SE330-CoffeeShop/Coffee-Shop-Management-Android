package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.data.dto.order.response.GetDetailOrderResponse;
import com.example.coffeeshopmanagementandroid.data.dto.order.response.OrderDetailResponse;
import com.example.coffeeshopmanagementandroid.domain.usecase.OrderUseCase;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailOrderViewModel extends ViewModel {
    private final OrderUseCase orderUseCase;
    private final MutableLiveData<String> address = new MutableLiveData<>();
    private final MutableLiveData<String> userName = new MutableLiveData<>();
    private final MutableLiveData<String> userPhoneNumber = new MutableLiveData<>();
    private final MutableLiveData<String> orderStatus = new MutableLiveData<>();
    private final MutableLiveData<List<OrderDetailResponse>> orderItems = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private final MutableLiveData<BigDecimal> totalPrice = new MutableLiveData<>();

    public OrderUseCase getOrderUseCase() {
        return orderUseCase;
    }

    public MutableLiveData<String> getAddress() {
        return address;
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<String> getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public MutableLiveData<String> getOrderStatus() {
        return orderStatus;
    }

    public MutableLiveData<BigDecimal> getTotalPrice() {
        return totalPrice;
    }

    public MutableLiveData<List<OrderDetailResponse>> getOrderItems() {
        return orderItems;
    }

    public void setIsLoading(Boolean loading) {
        isLoading.postValue(loading);
    }

    public void setErrorLiveData(String errorMessage) {
        errorLiveData.postValue(errorMessage);
    }

    @Inject
    public DetailOrderViewModel(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    public void fetchOrderDetail(String orderId) {
        setIsLoading(true);
        new Thread(() -> {
            try {
                // Fetch product detail
                GetDetailOrderResponse orderDetail = orderUseCase.getDetailOrder(orderId);
                address.postValue(orderDetail.getShippingAddressName());
                userName.postValue(orderDetail.getUserName());
                userPhoneNumber.postValue(orderDetail.getUserPhoneNumber());
                orderStatus.postValue(orderDetail.getOrderStatus());
                orderItems.postValue(orderDetail.getOrderDetails());
                totalPrice.postValue(orderDetail.getOrderTotalCost());
                // Fetch variant list by productId
                if (orderDetail == null) {
                    setErrorLiveData("Product not found");
                    return;
                }
            } catch (Exception e) {
                setErrorLiveData(e.getMessage());
                Log.e("DetailProductViewModel", "Failed to fetch product detail or variants: " + e.getMessage(), e);
            } finally {
                setIsLoading(false);
            }
        }).start();
    }
}
