package com.example.coffeeshopmanagementandroid.domain.usecase;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.response.AddressResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.CreateAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.GetAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.UpdateAddressRequest;
import com.example.coffeeshopmanagementandroid.domain.repository.AddressRepository;

import java.util.List;

public class AddressUseCase {
    private final AddressRepository addressRepository;
    public AddressUseCase(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public BasePagingResponse<List<AddressResponse>> getAddresses(GetAddressRequest request) {
        // Logic to get all cart items
        try {
            return addressRepository.getAddresses(request);
        } catch (Exception e) {
            // Handle exceptions, possibly rethrow or log
            throw new RuntimeException("Failed to retrieve cart items", e);
        }
    }

    public Void addAddress(CreateAddressRequest request) {
        // Logic to get all cart items
        try {
            return addressRepository.addAddress(request);
        } catch (Exception e) {
            // Handle exceptions, possibly rethrow or log
            throw new RuntimeException("Failed to retrieve cart items", e);
        }
    }

    public Void deleteAddress(String id) {
        // Logic to get all cart items
        try {
            return addressRepository.deleteAddress(id);
        } catch (Exception e) {
            // Handle exceptions, possibly rethrow or log
            throw new RuntimeException("Failed to retrieve cart items", e);
        }
    }

    public Void updateAddress(UpdateAddressRequest request) {
        // Logic to get all cart items
        try {
            return addressRepository.updateAddress(request);
        } catch (Exception e) {
            // Handle exceptions, possibly rethrow or log
            throw new RuntimeException("Failed to retrieve cart items", e);
        }
    }
}
