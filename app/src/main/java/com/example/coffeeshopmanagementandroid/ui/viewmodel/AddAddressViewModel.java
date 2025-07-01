package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.CreateAddressRequest;
import com.example.coffeeshopmanagementandroid.domain.usecase.AddressUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddAddressViewModel extends ViewModel {
    private final AddressUseCase addressUseCase;
    @Inject()
    public AddAddressViewModel(AddressUseCase addressUseCase) {
        this.addressUseCase = addressUseCase;
    }

    public void addAddress(String addressLine, String addressCity, String addressDistrict, Boolean addressIsDefault) {
        new Thread(() -> {
            try {
                CreateAddressRequest request = new CreateAddressRequest(addressLine, addressCity, addressDistrict, addressIsDefault);
                addressUseCase.addAddress(request);
                Log.d("AddAddressViewModel", "Address added successfully");
            } catch (Exception e) {
                Log.e("ConfirmViewModel", "Fetch all products failed: " + e.getMessage(), e);
            } finally {
                // Optionally, you can update UI or notify observers here
                // For example, you might want to clear input fields or show a success message
                Log.d("AddAddressViewModel", "Finished adding address");
            }
        }).start();
    }
}
