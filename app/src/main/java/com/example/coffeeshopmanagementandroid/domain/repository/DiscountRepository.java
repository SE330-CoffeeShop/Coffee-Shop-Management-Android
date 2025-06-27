package com.example.coffeeshopmanagementandroid.domain.repository;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.discount.request.GetAllDiscountByIdInRequest;
import com.example.coffeeshopmanagementandroid.data.dto.discount.response.DiscountResponse;

import java.util.List;

public interface DiscountRepository {
    BasePagingResponse<List<DiscountResponse>> findDiscountsByIdIn(GetAllDiscountByIdInRequest request) throws Exception;
}
