package com.example.coffeeshopmanagementandroid.data.repository;

import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.api.AddressService;
import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.response.AddressResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.CreateAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.GetAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.UpdateAddressRequest;
import com.example.coffeeshopmanagementandroid.domain.repository.AddressRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AddressRepositoryImpl implements AddressRepository {
    private AddressService addressService;

    public AddressRepositoryImpl(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public BasePagingResponse<List<AddressResponse>> getAddresses(GetAddressRequest request) throws Exception {
        Log.d("Address RepoIml", "Called");

        Call<BasePagingResponse<List<AddressResponse>>> call = addressService.getAddresses(request.getPage(), request.getLimit(), request.getSortType().toString(), request.getSortBy().toString());
        Response<BasePagingResponse<List<AddressResponse>>> response = call.execute();

        if (response.isSuccessful() && response.body() != null) {
            Log.d("Response", String.valueOf(response.body().getData()));
            return response.body();
        } else {
            String errorMessage = "Get products failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("GET PRODUCTS", errorMessage);
            throw new Exception(errorMessage);
        }
    }

    @Override
    public Void addAddress(CreateAddressRequest request) throws Exception {
        Log.d("Address RepoIml", "Called");

        Call<Void> call = addressService.addAddress(request);
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {
            Log.d("Response", "Address added successfully");
            return null;
        } else {
            String errorMessage = "Add address failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("ADD ADDRESS", errorMessage);
            throw new Exception(errorMessage);
        }
    }

    @Override
    public Void deleteAddress(String id) throws Exception {
        Log.d("Address RepoIml", "Called");

        Call<Void> call = addressService.deleteAddress(id);
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {
            Log.d("Response", "Address deleted successfully");
            return null;
        } else {
            String errorMessage = "Delete address failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("DELETE ADDRESS", errorMessage);
            throw new Exception(errorMessage);
        }
    }

    @Override
    public Void updateAddress(UpdateAddressRequest request) throws Exception {
        Log.d("Address RepoIml", "Called");

        Call<Void> call = addressService.updateAddress(request);
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {
            Log.d("Response", "Address updated successfully");
            return null;
        } else {
            String errorMessage = "Update address failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("UPDATE ADDRESS", errorMessage);
            throw new Exception(errorMessage);
        }
    }
}
