package com.example.coffeeshopmanagementandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductModel> products;
    private final OnAddToCartListener onAddToCart;
    private final OnItemClickListener onItemClick;

    public ProductAdapter(List<ProductModel> products, OnAddToCartListener onAddToCart, OnItemClickListener onItemClick) {
        this.products = products != null ? products : new ArrayList<>();
        this.onAddToCart = onAddToCart;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    // Hai interface cho 2 thao tác Xem chi tiết và Thêm vào giỏ hàng
    public interface OnAddToCartListener {
        void onAddToCart(ProductModel product);
    }

    public interface OnItemClickListener {
        void onItemClick(ProductModel product);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ImageView productImageView;
        private final TextView ratingTextView;
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView priceTextView;
        private final MaterialButton addButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            addButton = itemView.findViewById(R.id.addButton);

            // Xử lý sự kiện nhấn vào item
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    onItemClick.onItemClick(products.get(position));
                }
            });

            // Xử lý sự kiện nhấn vào nút thêm vào giỏ hàng
            addButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onAddToCart.onAddToCart(products.get(position));
                }
            });
        }

        public void bind(ProductModel product) {
            // Một vấn đề là ảnh được lưu như thế nào
            productImageView.setImageResource(R.drawable.demo_image);
            nameTextView.setText(product.getProductName());
            descriptionTextView.setText(product.getProductCategory());
            priceTextView.setText("$" + product.getProductPrice());
            ratingTextView.setText(String.valueOf(product.getProductRating()));
        }
    }
}