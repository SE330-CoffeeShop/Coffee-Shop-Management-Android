package com.example.coffeeshopmanagementandroid.data.mapper;

import com.example.coffeeshopmanagementandroid.data.dto.auth.response.LoginResponse;
import com.example.coffeeshopmanagementandroid.data.dto.product.response.ProductResponse;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static List<ProductModel> mapProductResponsesToProductsDomain(List<ProductResponse> productResponses) {
        List<ProductModel> productModels = new ArrayList<>();
        for (ProductResponse response : productResponses) {
            ProductModel model = new ProductModel();
            // Chuyển các trường từ ProductResponse sang ProductModel
            model.setProductId(response.getProductId());
            model.setProductName(response.getProductName());
            model.setProductDescription(response.getProductDescription());
            model.setProductPrice(response.getProductPrice());
            model.setProductThumb(response.getProductThumb());
            model.setProductCategoryId(response.getProductCategoryId());
            model.setProductSlug(response.getProductSlug());
            model.setProductIsPublished(response.getProductIsPublished());
            model.setProductIsDeleted(response.getProductIsDeleted());
            model.setProductRatingsAverage(response.getProductRatingsAverage());
            productModels.add(model);
        }
        return productModels;
    }
    public static ProductModel mapProductResponseToProductDomain(ProductResponse productResponse) {
        ProductModel model = new ProductModel();
        // Chuyển các trường từ ProductResponse sang ProductModel
        model.setProductId(productResponse.getProductId());
        model.setProductName(productResponse.getProductName());
        model.setProductDescription(productResponse.getProductDescription());
        model.setProductPrice(productResponse.getProductPrice());
        model.setProductThumb(productResponse.getProductThumb());
        model.setProductCategoryId(productResponse.getProductCategoryId());
        model.setProductSlug(productResponse.getProductSlug());
        model.setProductIsPublished(productResponse.getProductIsPublished());
        model.setProductIsDeleted(productResponse.getProductIsDeleted());
        model.setProductRatingsAverage(productResponse.getProductRatingsAverage());
        return model;
    }
}
