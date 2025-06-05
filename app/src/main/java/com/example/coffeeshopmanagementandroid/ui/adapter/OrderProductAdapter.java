package com.example.coffeeshopmanagementandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.OrderItemModel;

import java.util.ArrayList;
import java.util.List;

public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.OrderProductViewHolder> {
    private final List<OrderItemModel> productList;
    @NonNull
    @Override
    public OrderProductAdapter.OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_product_layout, parent, false);
        return new OrderProductViewHolder(view);
    }

    public OrderProductAdapter(List<OrderItemModel> productList) {
        this.productList = productList != null ? productList : new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductAdapter.OrderProductViewHolder holder, int position) {
        OrderItemModel product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return this.productList.size();
    }

    public class OrderProductViewHolder extends RecyclerView.ViewHolder {
        private final ImageView productImageView;
        private final TextView productNameTextView;
        private final TextView variantProductTextView;
        private final TextView priceTextView;
        private final TextView quantityTextView;

        public OrderProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            variantProductTextView = itemView.findViewById(R.id.variantProductTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
        }
        public void bind(OrderItemModel product) {
            // Bind dữ liệu vào các thành phần giao diện ở đây
//            productImageView.setImageResource(R.drawable.product_image);
            productNameTextView.setText(product.getProductName());
            variantProductTextView.setText(product.getVariant());
            priceTextView.setText(product.getUnitPrice() + "đ");
            quantityTextView.setText(String.valueOf(product.getQuantity()));
        }
    }
}
