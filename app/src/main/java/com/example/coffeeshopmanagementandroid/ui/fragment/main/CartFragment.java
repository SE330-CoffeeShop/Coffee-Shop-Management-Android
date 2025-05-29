package com.example.coffeeshopmanagementandroid.ui.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.ProductCartModel;
import com.example.coffeeshopmanagementandroid.ui.MainActivity;
import com.example.coffeeshopmanagementandroid.ui.adapter.ProductCartAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CartFragment extends Fragment {

    ProductCartAdapter productCartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        ImageButton backButton = view.findViewById(R.id.backButtonCart);
        backButton.setOnClickListener(v -> {
            // Xử lý khi nhấn nút back
            handleBackPressed();
        });

        List<ProductCartModel> productCarts = new ArrayList<>();

        productCarts.add(new ProductCartModel("PROD001", "Cappuccino", 45000, "Regular", 1));
        productCarts.add(new ProductCartModel("PROD002", "Latte", 50000, "Large", 2));
        productCarts.add(new ProductCartModel("PROD003", "Espresso", 35000, "Single", 1));
        productCarts.add(new ProductCartModel("PROD004", "Mocha", 55000, "Regular", 1));
        productCarts.add(new ProductCartModel("PROD005", "Americano", 40000, "Regular", 3));
        productCarts.add(new ProductCartModel("PROD006", "Macchiato", 52000, "Caramel", 1));
        productCarts.add(new ProductCartModel("PROD007", "Flat White", 48000, "Regular", 2));
        productCarts.add(new ProductCartModel("PROD008", "Cold Brew", 60000, "Nitro", 1));
        productCarts.add(new ProductCartModel("PROD009", "Hot Chocolate", 42000, "Marshmallow", 2));
        productCarts.add(new ProductCartModel("PROD010", "Iced Coffee", 47000, "Vanilla", 1));
        productCarts.add(new ProductCartModel("PROD011", "Cappuccino", 45000, "Regular", 1));

        productCartAdapter = new ProductCartAdapter(productCarts,
                this::onMinusProduct,
                this::onDeleteProduct,
                this::onPlusProduct,
                this::onItemClick);

        RecyclerView productCardRecyclerView = view.findViewById(R.id.productCardRecyclerView);
        productCardRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        productCardRecyclerView.setAdapter(productCartAdapter);

        return view;
    }

    private void onItemClick(ProductCartModel product) {
        Toast.makeText(requireContext(),
                "Click " + product.getProductName() + " on cart",
                Toast.LENGTH_SHORT).show();
    }

    private void onMinusProduct(ProductCartModel product) {
        Toast.makeText(requireContext(),
                "Click Minus " + product.getProductName() + " on cart",
                Toast.LENGTH_SHORT).show();
    }

    private void onPlusProduct(ProductCartModel product) {
        Toast.makeText(requireContext(),
                "Click Plus " + product.getProductName() + " on cart",
                Toast.LENGTH_SHORT).show();
    }

    private void onDeleteProduct(ProductCartModel product) {
        Toast.makeText(requireContext(),
                "Click Delete " + product.getProductName() + " on cart",
                Toast.LENGTH_SHORT).show();
    }

    private void handleBackPressed() {
        if (getView() != null) {
            Navigation.findNavController(getView()).popBackStack();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideBottomNavigation();
        }
    }

    @Override
    public void onPause() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showBottomNavigation();
        }
        super.onPause();
    }
}