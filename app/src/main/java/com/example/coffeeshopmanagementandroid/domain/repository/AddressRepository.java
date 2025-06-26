package com.example.coffeeshopmanagementandroid.domain.repository;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.response.AddressResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.CreateAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.GetAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.UpdateAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.request.GetAllCartItemRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.response.CartDetailResponse;

import java.util.List;

public interface AddressRepository {
    BasePagingResponse<List<AddressResponse>> getAddresses(GetAddressRequest request) throws Exception;

    Void addAddress(CreateAddressRequest request) throws Exception;

    Void deleteAddress(String id) throws Exception;

    Void updateAddress(UpdateAddressRequest request) throws Exception;
}
