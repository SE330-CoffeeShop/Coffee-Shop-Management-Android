package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.discount.request.GetAllDiscountByIdInRequest;
import com.example.coffeeshopmanagementandroid.data.dto.discount.response.DiscountResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.DiscountMapper;
import com.example.coffeeshopmanagementandroid.domain.model.DiscountModel;
import com.example.coffeeshopmanagementandroid.domain.usecase.DiscountUseCase;
import com.example.coffeeshopmanagementandroid.utils.enums.SortType;
import com.example.coffeeshopmanagementandroid.utils.enums.sortBy.DiscountSortBy;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DiscountViewModel extends ViewModel {
    private final DiscountUseCase discountUseCase;

    @Inject
    public DiscountViewModel(DiscountUseCase discountUseCase) {
        this.discountUseCase = discountUseCase;
    }

    private MutableLiveData<List<DiscountModel>> discountsLiveData = new MutableLiveData<>();

    public void FetchDiscountsByIdIn(List<String> discountIds, int page, int limit, SortType sortType, DiscountSortBy sortBy) throws Exception {
        GetAllDiscountByIdInRequest request = new GetAllDiscountByIdInRequest(page, limit, sortType, sortBy, discountIds);
        BasePagingResponse<List<DiscountResponse>> response = discountUseCase.findDiscountsByIdIn(request);
        if (response != null && response.getData() != null) {
            List<DiscountResponse> discountResponses = response.getData();
            List<DiscountModel> discountModels = DiscountMapper.mapToDiscountModels(discountResponses);
            discountsLiveData.postValue(discountModels);
        } else {
            Log.e("DiscountViewModel", "Fetch discounts failed: ");
        }
    }

    public void FetchAllDiscounts(int page, int limit, SortType sortType, DiscountSortBy sortBy) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                BasePagingResponse<List<DiscountResponse>> response = discountUseCase.findAllDiscounts(page, limit, sortType.toString(), sortBy.toString());
                if (response != null && response.getData() != null) {
                    List<DiscountResponse> discountResponses = response.getData();
                    List<DiscountModel> discountModels = DiscountMapper.mapToDiscountModels(discountResponses);
                    discountsLiveData.postValue(discountModels);
                } else {
                    Log.e("DiscountViewModel", "Fetch all discounts failed: ");
                }
            } catch (Exception e) {
                Log.e("DiscountViewModel", "Error fetching discounts", e);
            }
        });
    }

    public MutableLiveData<List<DiscountModel>> getDiscountsLiveData() {
        return discountsLiveData;
    }
}
