package com.example.coffeeshopmanagementandroid.data.api;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.order.response.OrderResponse;
import com.example.coffeeshopmanagementandroid.data.dto.product.response.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderService {
    @GET("/orders/customer/{customerId}")
    Call<BasePagingResponse<List<OrderResponse>>> getAllOrdersCustomer(@Path("customerId") String customerId,
                                                                       @Query("page") int page,
                                                                       @Query("limit") int limit,
                                                                       @Query("sortType") String sortType,
                                                                       @Query("sortBy") String sortBy
    );

    @POST("/orders/")
    Call<Void> createOrder(@Query("shippingAddressId") String addressId,
                           @Query("paymentMethodId") String paymentMethodId,
                           @Query("branchId") String branchId
    );
}
