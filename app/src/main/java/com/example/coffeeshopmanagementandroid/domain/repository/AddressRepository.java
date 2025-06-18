package com.example.coffeeshopmanagementandroid.domain.repository;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.response.AddressResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.GetAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.request.GetAllCartItemRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.response.CartDetailResponse;

import java.util.List;

public interface AddressRepository {
    BasePagingResponse<List<AddressResponse>> getAddresses(GetAddressRequest request) throws Exception;
}
