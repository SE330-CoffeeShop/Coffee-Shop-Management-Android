package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.domain.usecase.ProductUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final ProductUseCase productUseCase;
    private final MutableLiveData<List<CategoryModel>> _categories = new MutableLiveData<>();
    private final MutableLiveData<List<ProductModel>> _products = new MutableLiveData<>();
    private final MutableLiveData<List<ProductModel>> _recentProducts = new MutableLiveData<>();
    private final MutableLiveData<CategoryModel> _selectedType = new MutableLiveData<>(null);
    private final MutableLiveData<ProductModel> _navigateToProductDetail = new MutableLiveData<>();

    @Inject
    public HomeViewModel(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
//        fetchProducts();
//        fetchCategories();
//        fetchRecentProducts();
    }

    public LiveData<List<CategoryModel>> getCategories() {
        return _categories;
    }

    public LiveData<List<ProductModel>> getProducts() {
        return _products;
    }

    public LiveData<List<ProductModel>> getRecentProducts() {
        return _recentProducts;
    }

    public LiveData<CategoryModel> getSelectedType() {
        return _selectedType;
    }

    public LiveData<ProductModel> getNavigateToProductDetail() {
        return _navigateToProductDetail;
    }

//    private void fetchCategories() {
//        repository.getCategories().observeForever(coffeeTypes -> {
//            _categories.setValue(coffeeTypes);
//        });
//    }
//
//    private void fetchProducts() {
//        repository.getProducts().observeForever(coffees -> {
//            _products.setValue(coffees);
//        });
//    }
//
//    private void fetchRecentProducts() {
//        repository.getRecentProducts().observeForever(recentCoffees -> {
//            _recentProducts.setValue(recentCoffees);
//        });
//    }

    public void selectCoffeeType(CategoryModel category) {
        // Nếu loại đã được chọn trước đó, bỏ chọn (đặt lại thành null)
        if (_selectedType.getValue() != null && _selectedType.getValue().getCategoryId().equals(category.getCategoryId())) {
            _selectedType.setValue(null);
        } else {
            _selectedType.setValue(category);
        }
    }

    public List<ProductModel> getFilteredCoffees() {
        if (_selectedType.getValue() == null) {
            // Nếu không có loại nào được chọn, trả về toàn bộ danh sách
            return _products.getValue() != null ? _products.getValue() : new ArrayList<>();
        } else {
            // Lọc danh sách theo categoryId của loại được chọn
            return _products.getValue() != null ?
                    _products.getValue().stream()
                            .filter(coffee -> coffee.getProductCategory().equals(_selectedType.getValue().getCategoryId()))
                            .collect(Collectors.toList()) :
                    new ArrayList<>();
        }
    }

    public void addToCart(ProductModel product) {
        // Implement cart functionality here
        // For example, add to a cart repository or LiveData
    }

    public void navigateToProductDetail(ProductModel product) {
        _navigateToProductDetail.setValue(product);
    }
}