package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.CreateAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.UpdateAddressRequest;
import com.example.coffeeshopmanagementandroid.domain.usecase.AddressUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UpdateAddressViewModel extends ViewModel {
    private final AddressUseCase addressUseCase;
    @Inject()
    public UpdateAddressViewModel(AddressUseCase addressUseCase) {
        this.addressUseCase = addressUseCase;
    }

    public void updateAddress(String shippingAddressId, String addressLine, String addressCity, String addressDistrict, Boolean addressIsDefault) {
        new Thread(() -> {
            try {
                UpdateAddressRequest request = new UpdateAddressRequest(addressLine, addressCity, addressDistrict, addressIsDefault, shippingAddressId);
                addressUseCase.updateAddress(request);
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

    public void deleteAddress(String shippingAddressId) {
        new Thread(() -> {
            try {
                addressUseCase.deleteAddress(shippingAddressId);
                Log.d("DeleteAddressViewModel", "Address deleted successfully");
            } catch (Exception e) {
                Log.e("DeleteAddressViewModel", "Delete address failed: " + e.getMessage(), e);
            } finally {
                // Optionally, you can update UI or notify observers here
                Log.d("DeleteAddressViewModel", "Finished deleting address");
            }
        }).start();
    }
}
