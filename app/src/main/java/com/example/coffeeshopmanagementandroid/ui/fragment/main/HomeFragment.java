package com.example.coffeeshopmanagementandroid.ui.fragment.main;

import android.graphics.Rect;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.ui.activity.DetailProductActivity;
import com.example.coffeeshopmanagementandroid.ui.adapter.CategoryAdapter;
import com.example.coffeeshopmanagementandroid.ui.adapter.ProductAdapter;
import com.example.coffeeshopmanagementandroid.ui.fragment.cart.CartFragment;
import com.example.coffeeshopmanagementandroid.ui.viewmodel.ProductViewModel;
import com.example.coffeeshopmanagementandroid.utils.NavigationUtils;
import com.example.coffeeshopmanagementandroid.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter recentlyProductAdapter;
    private RecyclerView productRecyclerView;
    private RecyclerView categoryRecyclerView;
    private RecyclerView recentlyProductRecyclerView;
    private NavController navController;
    private ImageButton notificationButton;
    private ImageButton cartButton;
    private ProductViewModel productViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        navController = NavHostFragment.findNavController(this);
        NavigationUtils.checkAndFixNavState(navController, R.id.homeFragment, "HomeFragment");
        cartButton = view.findViewById(R.id.cartButton);
        notificationButton = view.findViewById(R.id.notificationButton);
        setUpRecyclerView(view);
        setUpListener();

        fetchAndObserveProducts();
    }

    private void setUpRecyclerView(View view) {
        List<ProductModel> coffees = new ArrayList<>();

        productAdapter = new ProductAdapter(coffees,
                // Xử lý thêm vào giỏ hàng
                product -> Toast.makeText(requireContext(),
                        "Added " + product.getProductName() + " to cart",
                        Toast.LENGTH_SHORT).show(),
                // Xử lý click vào sản phẩm
                product -> {
                    // Khi sản phẩm được click, chuyển tới màn hình chi tiết sản phẩm
                    Intent intent = new Intent(getActivity(), DetailProductActivity.class);
                    // Truyền thông tin sản phẩm vào Intent
                    intent.putExtra("product_id", product.getProductId());
                    intent.putExtra("product_name", product.getProductName());
                    intent.putExtra("product_description", product.getProductDescription());
                    intent.putExtra("product_price", product.getProductPrice());
                    intent.putExtra("product_image_url", product.getProductThumb());
                    intent.putExtra("product_rating", product.getProductRatingsAverage());
                    intent.putExtra("product_is_favorite", product.isFavorite());
                    intent.putExtra("product_category", product.getProductCategoryId());
                    startActivity(intent);  // Mở Activity chi tiết sản phẩm
                }
        );
        productRecyclerView = view.findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        productRecyclerView.setAdapter(productAdapter);

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

        categoryAdapter = new CategoryAdapter(coffeeTypes);
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);

        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(categoryAdapter);

        List<ProductModel> recentCoffees = new ArrayList<>();

        recentlyProductAdapter = new ProductAdapter(recentCoffees,
                // Xử lý thêm vào giỏ hàng
                product -> Toast.makeText(requireContext(),
                        "Added recently" + product.getProductName() + " to cart",
                        Toast.LENGTH_SHORT).show(),
                // Xử lý click vào sản phẩm
                product -> Toast.makeText(requireContext(),
                        "Selected recently: " + product.getProductName(),
                        Toast.LENGTH_SHORT).show());
        recentlyProductRecyclerView = view.findViewById(R.id.recentlyProductRecyclerView);
        recentlyProductRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recentlyProductRecyclerView.setAdapter(recentlyProductAdapter);

        int marginHorizontal = getResources().getDimensionPixelOffset(R.dimen.vertical_spacing);
        productRecyclerView.addItemDecoration(new SpaceItemDecoration().horizontal(marginHorizontal));
        recentlyProductRecyclerView.addItemDecoration(new SpaceItemDecoration().horizontal(marginHorizontal));
    }

    private void setUpListener() {
        cartButton.setOnClickListener(v ->
                NavigationUtils.safeNavigate(navController,
                        R.id.homeFragment,
                        R.id.action_homeFragment_to_cartFragment,
                        "CartFragment",
                        "HomeFragment"));
        notificationButton.setOnClickListener(v ->
                NavigationUtils.safeNavigate(navController,
                        R.id.homeFragment,
                        R.id.action_homeFragment_to_noticationFragment,
                        "NotificationFragment",
                        "HomeFragment"));
    }

    private void fetchAndObserveProducts() {
        productViewModel.fetchAllProducts(1, 10, "desc", "createdAt");

        productViewModel.getProductsResult().observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                productAdapter.setProducts(products);
            } else {
                productAdapter.setProducts(new ArrayList<>());
            }
        });
    }
}