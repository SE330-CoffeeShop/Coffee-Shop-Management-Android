package com.example.coffeeshopmanagementandroid.data.api;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.response.AddressResponse;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.CreateAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.address.resquest.UpdateAddressRequest;
import com.example.coffeeshopmanagementandroid.data.dto.cart.response.CartDetailResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AddressService {
    @GET("shipping-address/me")
    Call<BasePagingResponse<List<AddressResponse>>> getAddresses(@Query("page") int page,
                                                                   @Query("limit") int limit,
                                                                   @Query("sortType") String sortType,
                                                                   @Query("sortBy") String sortBy);

    @POST("shipping-address/")
    Call<Void> addAddress(@Body CreateAddressRequest request);

    @DELETE("shipping-address/{id}")
    Call<Void> deleteAddress(@Path("id") String id);

    @PATCH("shipping-address/")
    Call<Void> updateAddress(@Body UpdateAddressRequest request);
}
