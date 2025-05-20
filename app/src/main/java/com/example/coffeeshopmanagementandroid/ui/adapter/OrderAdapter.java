package com.example.coffeeshopmanagementandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.OrderModel;
import com.example.coffeeshopmanagementandroid.ui.component.StatusIndicator;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<OrderModel> orderList;

    public OrderAdapter(List<OrderModel> orders) {
        this.orderList = orders != null ? orders : new ArrayList<>();
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        OrderModel order = orderList.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderIdTextView;
        StatusIndicator statusIndicator;
        TextView totalOrderTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderIdTextView = itemView.findViewById(R.id.orderIdTextView);
            statusIndicator = itemView.findViewById(R.id.statusIndicator);
            totalOrderTextView = itemView.findViewById(R.id.totalOrderTextView);
        }

        public void bind(OrderModel order) {
            // Bind dữ liệu vào các thành phần giao diện ở đây
            orderIdTextView.setText(order.getOrderId());
            statusIndicator.setStatus(order.getOrderStatus());
            totalOrderTextView.setText(String.valueOf(order.getTotalOrder()) + "đ");
        }
    }
}
