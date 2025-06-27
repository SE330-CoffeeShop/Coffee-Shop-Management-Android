package com.example.coffeeshopmanagementandroid.domain.usecase;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.discount.request.GetAllDiscountByIdInRequest;
import com.example.coffeeshopmanagementandroid.data.dto.discount.response.DiscountResponse;
import com.example.coffeeshopmanagementandroid.domain.repository.DiscountRepository;

import java.util.List;

public class DiscountUseCase {
    private final DiscountRepository discountRepository;

    public DiscountUseCase(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @SuppressLint("LongLogTag")
    public BasePagingResponse<List<DiscountResponse>> findDiscountsByIdIn(GetAllDiscountByIdInRequest request) throws Exception {
        Log.d("Discount Use Case - findDiscountsByIdIn", "Called with request: " + request);
        return discountRepository.findDiscountsByIdIn(request);
    }
}
