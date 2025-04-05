package com.example.coffeeshopmanagementandroid.ui.fragment.main;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.ui.adapter.CategoryAdapter;
import com.example.coffeeshopmanagementandroid.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoritesFragment extends Fragment {

    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        // Category
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
        RecyclerView categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);

        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(categoryAdapter);

        // Product
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

        productAdapter = new ProductAdapter(coffees,
                // Xử lý thêm vào giỏ hàng
                product -> Toast.makeText(requireContext(),
                        "Added " + product.getProductName() + " to cart",
                        Toast.LENGTH_SHORT).show(),
                // Xử lý click vào sản phẩm
                product -> Toast.makeText(requireContext(),
                        "Selected: " + product.getProductName(),
                        Toast.LENGTH_SHORT).show()
        );
        RecyclerView productRecyclerView = view.findViewById(R.id.productRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        productRecyclerView.setAdapter(productAdapter);


        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        productRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels));
        return view;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;

        public GridSpacingItemDecoration(int spanCount, int spacing) {
            this.spanCount = spanCount;
            this.spacing = spacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            // Khoảng cách giữa các cột (giữa item trái và phải)
            int horizontalSpacing = spacing;

            // Khoảng cách giữa các item trong cùng cột (dọc)
            int verticalSpacing = spacing;

            if (column == 0) {
                outRect.left = 0;
                outRect.right = horizontalSpacing / 2;
            } else {
                outRect.left = horizontalSpacing / 2;
                outRect.right = 0;
            }

            // Thêm khoảng cách dọc cho tất cả item trừ item đầu tiên
            if (position >= spanCount) {
                outRect.top = verticalSpacing;
            }
        }
    }
}