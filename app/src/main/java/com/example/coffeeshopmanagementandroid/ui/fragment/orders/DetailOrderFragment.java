package com.example.coffeeshopmanagementandroid.ui.fragment.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.OrderItemModel;
import com.example.coffeeshopmanagementandroid.ui.MainActivity;
import com.example.coffeeshopmanagementandroid.ui.adapter.OrderProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderFragment extends Fragment {
    private OrderProductAdapter orderProductAdapter;
    private RecyclerView orderProductRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        View view = inflater.inflate(R.layout.fragment_detail_order, container, false);

        ImageButton backButton = view.findViewById(R.id.backButtonOrder);
        backButton.setOnClickListener(v -> {
            handleBackPressed();
        });

        setupRecyclerView(view);
        return view;
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

    private void setupRecyclerView(View view) {
        List<OrderItemModel> products = new ArrayList<>();
        products.add(new OrderItemModel("https://example.com/cappuccino.jpg", "Classic Cappuccino", "Size M, 100% đá, 50% đường", 2, 45.13));
        products.add(new OrderItemModel("https://example.com/caramel_macchiato.jpg", "Caramel Macchiato", "Size L, 70% đá, 100% đường", 1, 50.00));
        products.add(new OrderItemModel("https://example.com/vanilla_latte.jpg", "Vanilla Latte", "Size S, 50% đá, 30% đường", 3, 48.75));
        products.add(new OrderItemModel("https://example.com/americano.jpg", "Americano", "Size M, không đá, không đường", 2, 35.50));

        orderProductRecyclerView = view.findViewById(R.id.orderProductRecyclerView);
        orderProductRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        orderProductAdapter = new OrderProductAdapter(products);
        orderProductRecyclerView.setAdapter(orderProductAdapter);
    }
}