package com.example.coffeeshopmanagementandroid.data.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.api.DiscountService;
import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.discount.request.GetAllDiscountByIdInRequest;
import com.example.coffeeshopmanagementandroid.data.dto.discount.response.DiscountResponse;
import com.example.coffeeshopmanagementandroid.domain.repository.DiscountRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class DiscountRepositoryImpl implements DiscountRepository {

    private final DiscountService discountService;

    public DiscountRepositoryImpl(DiscountService discountService) {
        this.discountService = discountService;
    }

    @SuppressLint("LongLogTag")
    @Override
    public BasePagingResponse<List<DiscountResponse>> findDiscountsByIdIn(GetAllDiscountByIdInRequest request) throws Exception {
        Log.d("Discount RepoIml - findDiscountsByIdIn", "Called");
        Call<BasePagingResponse<List<DiscountResponse>>> call = discountService.findDiscountsByIds(request.getDiscountIds(), request.getPage(), request.getLimit(), request.getSortType().toString(), request.getSortBy().toString());
        Response<BasePagingResponse<List<DiscountResponse>>> response = call.execute();
        Log.d("Discount RepoIml - findDiscountsByIdIn", "Response: " + response.toString());
        if (response.isSuccessful() && response.body() != null) {
            Log.d("Response", String.valueOf(response.body().getData()));
            return response.body();
        } else {
            String errorMessage = "Get discounts failed: " + (response.errorBody() != null ? response.errorBody().string() : "Unknown error");
            Log.e("GET DISCOUNTS", errorMessage);
            throw new Exception(errorMessage);
        }
    }
}
