package com.example.coffeeshopmanagementandroid.ui.fragment.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.OrderItemModel;
import com.example.coffeeshopmanagementandroid.domain.model.OrderModel;
import com.example.coffeeshopmanagementandroid.domain.model.OrderStatusModel;
import com.example.coffeeshopmanagementandroid.ui.adapter.OrderAdapter;
import com.example.coffeeshopmanagementandroid.ui.adapter.StatusAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrdersFragment extends Fragment {
    private OrderAdapter orderAdapter;
    private StatusAdapter orderStatusAdapter;
    private RecyclerView orderRecyclerView;
    private RecyclerView orderStatusRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        setupRecyclerView(view);
//        observeOrders();
        return view;
    }

    private void setupRecyclerView(View view) {
        List<OrderModel> orders = new ArrayList<>();
        orders.add(new OrderModel("ORD001", "Chờ tiếp nhận", Arrays.asList(new OrderItemModel("https://example.com/cappuccino.jpg", "Classic Cappuccino", "Size M, 100% đá, 50% đường", 2, 45.13), new OrderItemModel("https://example.com/caramel_macchiato.jpg", "Caramel Macchiato", "Size L, 70% đá, 100% đường", 1, 50.00))));
        orders.add(new OrderModel("ORD002", "Chờ chuẩn bị", Arrays.asList(new OrderItemModel("https://example.com/vanilla_latte.jpg", "Vanilla Latte", "Size S, 50% đá, 30% đường", 3, 48.75), new OrderItemModel("https://example.com/americano.jpg", "Americano", "Size M, không đá, không đường", 2, 35.50))));
        orders.add(new OrderModel("ORD003", "Đang giao hàng", Arrays.asList(new OrderItemModel("https://example.com/mocha.jpg", "Chocolate Mocha", "Size L, 30% đá, 70% đường", 2, 55.00), new OrderItemModel("https://example.com/cold_brew.jpg", "Cold Brew", "Size M, 100% đá, không đường", 1, 60.00))));
        orders.add(new OrderModel("ORD004", "Đã giao", List.of(new OrderItemModel("https://example.com/flat_white.jpg", "Flat White", "Size S, không đá, 20% đường", 1, 47.00))));
        orders.add(new OrderModel("ORD005", "Đã huỷ", Arrays.asList(new OrderItemModel("https://example.com/iced_latte.jpg", "Iced Latte", "Size L, 50% đá, 50% đường", 2, 52.00), new OrderItemModel("https://example.com/decaf_cappuccino.jpg", "Decaf Cappuccino", "Size M, không đá, 10% đường", 1, 45.13))));
        orders.add(new OrderModel("ORD006", "Chờ tiếp nhận", Arrays.asList(new OrderItemModel("https://example.com/espresso.jpg", "Espresso", "Size S, không đá, không đường", 1, 40.00), new OrderItemModel("https://example.com/mocha_white.jpg", "White Mocha", "Size XL, 30% đá, 80% đường", 2, 58.00))));
        orders.add(new OrderModel("ORD007", "Đang xử lý", Arrays.asList(new OrderItemModel("https://example.com/latte.jpg", "Latte", "Size M, 20% đá, 40% đường", 3, 45.50), new OrderItemModel("https://example.com/black_tea.jpg", "Black Tea", "Size L, 70% đá, 60% đường", 1, 30.00))));
        orders.add(new OrderModel("ORD008", "Đang giao hàng", List.of(new OrderItemModel("https://example.com/green_tea.jpg", "Green Tea", "Size M, 50% đá, 30% đường", 2, 35.00))));
        orders.add(new OrderModel("ORD009", "Đã giao", Arrays.asList(new OrderItemModel("https://example.com/hot_chocolate.jpg", "Hot Chocolate", "Size L, không đá, 100% đường", 1, 50.00), new OrderItemModel("https://example.com/caramel_latte.jpg", "Caramel Latte", "Size XL, 10% đá, 90% đường", 1, 52.00))));
        orders.add(new OrderModel("ORD010", "Đã huỷ", List.of(new OrderItemModel("https://example.com/tea_latte.jpg", "Tea Latte", "Size S, 40% đá, 20% đường", 2, 42.00))));

        orderRecyclerView = view.findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        orderAdapter = new OrderAdapter(orders, order -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_orderFragment_to_detailOrderFragment);
        });
        orderRecyclerView.setAdapter(orderAdapter);

        List<OrderStatusModel> statuses = new ArrayList<>();
        statuses.add(new OrderStatusModel("Chờ tiếp nhận"));
        statuses.add(new OrderStatusModel("Chờ chuẩn bị"));
        statuses.add(new OrderStatusModel("Đang giao hàng"));
        statuses.add(new OrderStatusModel("Đã giao"));
        statuses.add(new OrderStatusModel("Đã huỷ"));
        statuses.add(new OrderStatusModel("Đã thanh toán"));
        statuses.add(new OrderStatusModel("Đang xử lý"));
        statuses.add(new OrderStatusModel("Đã xác nhận"));
        statuses.add(new OrderStatusModel("Đã đóng gói"));
        statuses.add(new OrderStatusModel("Đã hoàn tiền"));
        statuses.add(new OrderStatusModel("Đang xử lý trả lại"));
        statuses.add(new OrderStatusModel("Đã trả lại"));
        statuses.add(new OrderStatusModel("Hoàn tất"));

        orderStatusRecyclerView = view.findViewById(R.id.orderStatusRecyclerView);
        orderStatusRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        orderStatusAdapter = new StatusAdapter(statuses);
        orderStatusRecyclerView.setAdapter(orderStatusAdapter);
    }
}