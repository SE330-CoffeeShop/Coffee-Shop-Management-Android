package com.example.coffeeshopmanagementandroid.domain.repository;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.cart.request.AddToCartRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.request.GetAllCartItemRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.request.UpdateCartRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.response.CartDetailResponse;
import com.example.coffeeshopmanagementandroid.data.dto.product.request.GetAllProductVariantsRequest;
import com.example.coffeeshopmanagementandroid.domain.model.cart.CartItemModel;
import com.example.coffeeshopmanagementandroid.domain.model.product.ProductVariantModel;

import java.util.List;

public interface CartRepository {
    String addToCart(AddToCartRequest request);

    BasePagingResponse<List<CartDetailResponse>> getCartItem(GetAllCartItemRequest request) throws Exception;

    Void updateCartItem(UpdateCartRequest request) throws Exception;
}
