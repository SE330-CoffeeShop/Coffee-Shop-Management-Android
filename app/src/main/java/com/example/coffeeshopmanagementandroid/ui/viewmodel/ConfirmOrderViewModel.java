package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.response.AddressResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.GetAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.request.GetAllCartItemRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.response.CartDetailResponse;
import com.example.coffeeshopmanagementandroid.data.dto.order.request.CreateOrderRequest;
import com.example.coffeeshopmanagementandroid.data.dto.order.response.OrderResponse;
import com.example.coffeeshopmanagementandroid.data.dto.payment.request.GetAllPaymentRequest;
import com.example.coffeeshopmanagementandroid.data.dto.payment.response.PaymentResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.AddressMapper;
import com.example.coffeeshopmanagementandroid.data.mapper.CartMapper;
import com.example.coffeeshopmanagementandroid.data.mapper.PaymentMapper;
import com.example.coffeeshopmanagementandroid.domain.model.address.AddressModel;
import com.example.coffeeshopmanagementandroid.domain.model.cart.CartItemModel;
import com.example.coffeeshopmanagementandroid.domain.model.payment.PaymentMethodModel;
import com.example.coffeeshopmanagementandroid.domain.usecase.AddressUseCase;
import com.example.coffeeshopmanagementandroid.domain.usecase.CartUseCase;
import com.example.coffeeshopmanagementandroid.domain.usecase.OrderUseCase;
import com.example.coffeeshopmanagementandroid.domain.usecase.PaymentUseCase;
import com.example.coffeeshopmanagementandroid.utils.enums.SortType;
import com.example.coffeeshopmanagementandroid.utils.enums.sortBy.AddressSortBy;
import com.example.coffeeshopmanagementandroid.utils.enums.sortBy.CartSortBy;
import com.example.coffeeshopmanagementandroid.utils.enums.sortBy.PaymentSortBy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ConfirmOrderViewModel extends ViewModel {
    private final CartUseCase cartUseCase;
    private final PaymentUseCase paymentUseCase;
    private final AddressUseCase addressUseCase;
    private final OrderUseCase orderUseCase;
    private final MutableLiveData<List<CartItemModel>> cartItemsLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<PaymentMethodModel>> paymentMethodsLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<AddressModel>> addressesLiveDate = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<AddressModel> selectedAddress = new MutableLiveData<>(null);
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<Integer> page = new MutableLiveData<>(1);
    private final MutableLiveData<Integer> limit = new MutableLiveData<>(10);
    private final MutableLiveData<Integer> total = new MutableLiveData<>(null);
    private final MutableLiveData<Double> totalPrice = new MutableLiveData<>(0.0);

    private final MutableLiveData<String> approvalLinkLiveData = new MutableLiveData<>();
    public MutableLiveData<String> getApprovalLinkLiveData() {
        return approvalLinkLiveData;
    }

    public void setIsLoading(Boolean loading) {
        isLoading.postValue(loading);
    }
    private void setPage(int page) {
        this.page.postValue(page);
    }

    private void setTotal(int total) {
        this.total.postValue(total);
    }
    public void setErrorLiveData(String errorMessage) {
        errorLiveData.postValue(errorMessage);
    }
    @Inject
    public ConfirmOrderViewModel(CartUseCase cartUseCase, PaymentUseCase paymentUseCase, AddressUseCase addressUseCase, OrderUseCase orderUseCase) {
        this.cartUseCase = cartUseCase;
        this.paymentUseCase = paymentUseCase;
        this.addressUseCase = addressUseCase;
        this.orderUseCase = orderUseCase;
    }

    public MutableLiveData<List<CartItemModel>> getCartItemsLiveData() {
        return cartItemsLiveData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public MutableLiveData<Double> getTotalPrice() {
        return totalPrice;
    }

    public MutableLiveData<List<PaymentMethodModel>> getPaymentMethodsLiveData() {
        return paymentMethodsLiveData;
    }

    public MutableLiveData<List<AddressModel>> getAddressesLiveData() {
        return addressesLiveDate;
    }

    public MutableLiveData<AddressModel> getSelectedAddress() {
        return selectedAddress;
    }

    public void fetchAllCartItems(int page, int limit, SortType sortType, CartSortBy sortBy) {
        setIsLoading(true);
        new Thread(() -> {
            try {
                GetAllCartItemRequest request = new GetAllCartItemRequest(page, limit, sortType, sortBy);
                BasePagingResponse<List<CartDetailResponse>> result = cartUseCase.getCartItems(request);
                if (result != null && result.getData() != null) {
                    setTotal(result.getPaging().getTotal());
                    List<CartItemModel> cartItems = CartMapper.mapToCartItemModels(result.getData());
                    appendCartItems(cartItems);
                    calculateTotalPrice(cartItems);
                }
            } catch (Exception e) {
                setErrorLiveData(e.getMessage());
                Log.e("ConfirmViewModel", "Fetch all products failed: " + e.getMessage(), e);
            } finally {
                setIsLoading(false);
            }
        }).start();
    }

    public void fetchAllPaymentMethods() {
        setIsLoading(true);
        new Thread(() -> {
            try {
                GetAllPaymentRequest request = new GetAllPaymentRequest(1, 10, SortType.ASC, PaymentSortBy.CREATED_AT);
                BasePagingResponse<List<PaymentResponse>> result = paymentUseCase.getPaymentMethods(request);
                if (result != null) {
                    List<PaymentMethodModel> paymentMethods = PaymentMapper.mapToPaymentModels(result.getData());
                    List<PaymentMethodModel> currentPaymentMethods = new ArrayList<>();
                    for( PaymentMethodModel paymentMethod : paymentMethods) {
                        if(paymentMethod.getActive() != false) {
                            currentPaymentMethods.add(paymentMethod);
                        }
                    }
                    appendPaymentMethod(currentPaymentMethods);
                }
            } catch (Exception e) {
                setErrorLiveData(e.getMessage());
                Log.e("ConfirmOrderViewModel", "Fetch all payment methods failed: " + e.getMessage(), e);
            } finally {
                setIsLoading(false);
            }
        }).start();
    }

    public void fetchAllAddresses() {
        setIsLoading(true);
        new Thread(() -> {
            try {
                GetAddressRequest request = new GetAddressRequest(1, 10, SortType.ASC, AddressSortBy.CREATED_AT);
                BasePagingResponse<List<AddressResponse>> result = addressUseCase.getAddresses(request);
                if (result != null && result.getData() != null) {
                    setTotal(result.getPaging().getTotal());
                    List<AddressModel> addresses = AddressMapper.mapToAddressModelList(result.getData());
                    appendAddresses(addresses);
                    for( AddressModel address : addresses) {
                        if (address.getAddressIsDefault() == true) {
                            selectedAddress.postValue(address);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                setErrorLiveData(e.getMessage());
                Log.e("ConfirmViewModel", "Fetch all products failed: " + e.getMessage(), e);
            } finally {
                setIsLoading(false);
            }
        }).start();
    }

    public void createOrder(String shippingAddressId, String paymentMethodId, String branchId) {
        setIsLoading(true);
        new Thread(() -> {
            try {
                CreateOrderRequest request = new CreateOrderRequest(shippingAddressId, paymentMethodId, branchId);
                OrderResponse orderResponse = orderUseCase.createOrder(request);
                if (orderResponse.getApprovalLink() != null) {
                    approvalLinkLiveData.postValue(orderResponse.getApprovalLink());
                }
                Log.d("ConfirmOrderViewModel", "Order created successfully");
            } catch (Exception e) {
                setErrorLiveData(e.getMessage());
                Log.e("ConfirmOrderViewModel", "Create order failed: " + e.getMessage(), e);
            } finally {
                setIsLoading(false);
            }
        }).start();
    }

    private void calculateTotalPrice(List<CartItemModel> cartItems) {
        double total = 0.0;
        for (CartItemModel item : cartItems) {
            total += item.getCartDetailUnitPrice() * item.getCartDetailQuantity();
        }
        totalPrice.postValue(total);
    }

    public void appendAddresses(List<AddressModel> addresses) {
        List<AddressModel> currentAddresses = new ArrayList<>();
        for (AddressModel newItem : addresses) {
            if (!currentAddresses.contains(newItem)) {
                currentAddresses.add(newItem);
            }
        }
        addressesLiveDate.postValue(currentAddresses);
    }

    public void appendPaymentMethod(List<PaymentMethodModel> paymentMethods) {
        List<PaymentMethodModel> currentPaymentMethods = new ArrayList<>();
        for (PaymentMethodModel newMethod : paymentMethods) {
            if (!currentPaymentMethods.contains(newMethod)) {
                currentPaymentMethods.add(newMethod);
            }
        }
        paymentMethodsLiveData.postValue(currentPaymentMethods);
    }

    private void appendCartItems(List<CartItemModel> cartItems) {
        List<CartItemModel> currentCartItems = new ArrayList<>();

        for (CartItemModel newItem : cartItems) {
            if (!currentCartItems.contains(newItem)) {
                currentCartItems.add(newItem);
            }
        }

        cartItemsLiveData.postValue(currentCartItems);
    }
}
