package com.example.coffeeshopmanagementandroid.domain.usecase;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.product.request.GetAllProductsRequest;
import com.example.coffeeshopmanagementandroid.data.dto.product.response.ProductResponse;
import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.domain.repository.ProductRepository;

import java.util.List;

public class ProductUseCase {
    private final ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BasePagingResponse<List<ProductResponse>> getAllProducts(GetAllProductsRequest request) throws Exception {
        Log.d("Product Use Case", "Called");
        return productRepository.getAllProducts(request);
//        return null;
    }

    public List<ProductModel> getRecentProducts() throws Exception {
        return productRepository.getAllRecentProducts();
    }
}
