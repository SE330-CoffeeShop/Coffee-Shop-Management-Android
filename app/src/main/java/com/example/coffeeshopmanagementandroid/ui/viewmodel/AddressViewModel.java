package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.response.AddressResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.GetAddressRequest;
import com.example.coffeeshopmanagementandroid.data.mapper.AddressMapper;
import com.example.coffeeshopmanagementandroid.domain.model.address.AddressModel;
import com.example.coffeeshopmanagementandroid.domain.usecase.AddressUseCase;
import com.example.coffeeshopmanagementandroid.utils.enums.SortType;
import com.example.coffeeshopmanagementandroid.utils.enums.sortBy.AddressSortBy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddressViewModel extends ViewModel {
    private final AddressUseCase addressUseCase;
    private final MutableLiveData<List<AddressModel>> addressesLiveDate = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<Integer> page = new MutableLiveData<>(1);
    private final MutableLiveData<Integer> limit = new MutableLiveData<>(10);
    private final MutableLiveData<Integer> total = new MutableLiveData<>(null);
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<AddressModel> selectedAddress = new MutableLiveData<>(null);

    public void setIsLoading(Boolean loading) {
        isLoading.postValue(loading);
    }
    private void setTotal(int total) {
        this.total.postValue(total);
    }
    public void setErrorLiveData(String errorMessage) {
        errorLiveData.postValue(errorMessage);
    }
    public MutableLiveData<List<AddressModel>> getAddressesLiveData() {
        return addressesLiveDate;
    }
    @Inject()
    public AddressViewModel(AddressUseCase addressUseCase) {
        this.addressUseCase = addressUseCase;
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

    public void appendAddresses(List<AddressModel> addresses) {
        List<AddressModel> currentAddresses = new ArrayList<>();
        for (AddressModel newItem : addresses) {
            if (!currentAddresses.contains(newItem)) {
                currentAddresses.add(newItem);
            }
        }
        addressesLiveDate.postValue(addresses);
    }
}
