package com.example.coffeeshopmanagementandroid.data.mapper;

import com.example.coffeeshopmanagementandroid.data.dto.favoriteproduct.response.FavoriteProductResponse;
import com.example.coffeeshopmanagementandroid.domain.model.FavoriteProductModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteProductMapper {
    // Do API trả về một mảng String, không phải mảng các đối tượng, sau này cập nhật API thì sửa lại
    public static List<FavoriteProductModel> mapFavoriteProductResponsesToProductsDomain(List<String> FavoriteProductResponses) {
        List<FavoriteProductModel> favoriteProductModels = new ArrayList<>();
        for (String response : FavoriteProductResponses) {
            FavoriteProductModel model = new FavoriteProductModel();
            model.setFavoriteProductId(response);
            favoriteProductModels.add(model);
        }
        return favoriteProductModels;
    }
}
