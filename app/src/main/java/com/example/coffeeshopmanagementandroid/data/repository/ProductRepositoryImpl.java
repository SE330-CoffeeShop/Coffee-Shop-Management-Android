package com.example.coffeeshopmanagementandroid.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coffeeshopmanagementandroid.data.api.ProductService;
import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.domain.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductService productService;
    public ProductRepositoryImpl(ProductService productService) {
        this.productService = productService;
    }

    private MutableLiveData<List<CategoryModel>> _categories = new MutableLiveData<>(getDummyCoffeeTypes());
    private MutableLiveData<List<ProductModel>> _coffees = new MutableLiveData<>(getDummyCoffees());
    private MutableLiveData<List<ProductModel>> _recentCoffees = new MutableLiveData<>(getDummyRecentCoffees());

    @Override
    public LiveData<List<CategoryModel>> getCategories() {
        return _categories;
    }

    @Override
    public LiveData<List<ProductModel>> getProducts() {
        return _coffees;
    }

    @Override
    public LiveData<List<ProductModel>> getRecentProducts() {
        return _recentCoffees;
    }

    private List<CategoryModel> getDummyCoffeeTypes() {
        List<CategoryModel> coffeeTypes = new ArrayList<>();
        coffeeTypes.add(new CategoryModel("1", "Cappuccino", "A classic Italian coffee with frothy milk."));
        coffeeTypes.add(new CategoryModel("2", "Macchiato", "Espresso with a small amount of steamed milk."));
        coffeeTypes.add(new CategoryModel("3", "Latte", "Espresso with steamed milk and a small amount of foam."));
        coffeeTypes.add(new CategoryModel("4", "Espresso", "A strong black coffee made by forcing steam through ground coffee beans."));
        coffeeTypes.add(new CategoryModel("5", "Americano", "Espresso diluted with hot water, similar to drip coffee."));
        coffeeTypes.add(new CategoryModel("6", "Mocha", "Espresso with chocolate and steamed milk, often topped with whipped cream."));
        coffeeTypes.add(new CategoryModel("7", "Flat White", "Similar to a latte but with a higher ratio of coffee to milk."));
        coffeeTypes.add(new CategoryModel("8", "Decaf", "Decaffeinated coffee for those who want to avoid caffeine."));
        coffeeTypes.add(new CategoryModel("9", "Iced Coffee", "Chilled coffee served with ice, often sweetened."));
        coffeeTypes.add(new CategoryModel("10", "Cold Brew", "Coffee brewed with cold water over a long period, resulting in a smooth flavor."));
        return coffeeTypes;
    }

    private List<ProductModel> getDummyCoffees() {
        List<ProductModel> coffees = new ArrayList<>();
        coffees.add(new ProductModel("1", "Classic Cappuccino", 45.13, "https://example.com/cappuccino.jpg", 4.5f, "Cappuccino", false));
        coffees.add(new ProductModel("2", "Caramel Macchiato", 50.00, "https://example.com/caramel_macchiato.jpg", 4.7f, "Macchiato", false));
        coffees.add(new ProductModel("3", "Vanilla Latte", 48.75, "https://example.com/vanilla_latte.jpg", 4.6f, "Latte", false));
        coffees.add(new ProductModel("4", "Espresso Shot", 30.00, "https://example.com/espresso.jpg", 4.8f, "Espresso", false));
        coffees.add(new ProductModel("5", "Americano", 35.50, "https://example.com/americano.jpg", 4.4f, "Americano", false));
        coffees.add(new ProductModel("6", "Chocolate Mocha", 55.00, "https://example.com/mocha.jpg", 4.9f, "Mocha", false));
        coffees.add(new ProductModel("7", "Flat White", 47.00, "https://example.com/flat_white.jpg", 4.7f, "Flat White", false));
        coffees.add(new ProductModel("8", "Decaf Cappuccino", 45.13, "https://example.com/decaf_cappuccino.jpg", 4.3f, "Decaf", false));
        coffees.add(new ProductModel("9", "Iced Latte", 52.00, "https://example.com/iced_latte.jpg", 4.6f, "Iced Coffee", false));
        coffees.add(new ProductModel("10", "Cold Brew", 60.00, "https://example.com/cold_brew.jpg", 4.9f, "Cold Brew", false));
        return coffees;
    }

    private List<ProductModel> getDummyRecentCoffees() {
        List<ProductModel> recentCoffees = new ArrayList<>();
        recentCoffees.add(new ProductModel("11", "Hazelnut Latte", 49.00, "https://example.com/hazelnut_latte.jpg", 4.8f, "Latte", false));
        recentCoffees.add(new ProductModel("12", "Iced Caramel Macchiato", 53.00, "https://example.com/iced_caramel_macchiato.jpg", 4.7f, "Iced Coffee", false));
        recentCoffees.add(new ProductModel("13", "Pumpkin Spice Latte", 56.00, "https://example.com/pumpkin_spice_latte.jpg", 4.9f, "Latte", false));
        recentCoffees.add(new ProductModel("14", "Double Espresso", 40.00, "https://example.com/double_espresso.jpg", 4.6f, "Espresso", false));
        recentCoffees.add(new ProductModel("15", "Vanilla Sweet Cream Cold Brew", 62.00, "https://example.com/vanilla_cold_brew.jpg", 5.0f, "Cold Brew", false));
        return recentCoffees;
    }
}