package com.example.coffeeshopmanagementandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.DiscountModel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DiscountHomeAdapter extends RecyclerView.Adapter<DiscountHomeAdapter.DiscountHomeViewHolder> {
    private final List<DiscountModel> discounts;
    private final OnDiscountSelectedListener listener;

    public DiscountHomeAdapter(List<DiscountModel> discounts, OnDiscountSelectedListener listener) {
        this.discounts = discounts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DiscountHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discount_home_item_layout, parent, false);
        return new DiscountHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountHomeViewHolder holder, int position) {
        DiscountModel discount = discounts.get(position);
        holder.bind(discount);
    }

    @Override
    public int getItemCount() {
        return discounts.size();
    }

    public class DiscountHomeViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivDiscountImage;
        private final TextView tvDiscountName;
        private final TextView tvDiscountStartDate;
        private final TextView tvDiscountEndDate;

        public DiscountHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDiscountImage = itemView.findViewById(R.id.ivDiscountImage);
            tvDiscountName = itemView.findViewById(R.id.tvDiscountName);
            tvDiscountStartDate = itemView.findViewById(R.id.tvDiscountStartDate);
            tvDiscountEndDate = itemView.findViewById(R.id.tvDiscountEndDate);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDiscountSelected(discounts.get(position));
                }
            });
        }

        public void bind(DiscountModel discount) {
            ivDiscountImage.setImageResource(R.drawable.discount_icon);
            tvDiscountName.setText(discount.getDiscountName());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", Locale.getDefault());
            String start = sdf.format(discount.getDiscountStartDate());
            String end = sdf.format(discount.getDiscountEndDate());
            tvDiscountStartDate.setText(start);
            tvDiscountEndDate.setText(" - " + end);
        }
    }

    public void setDiscounts(List<DiscountModel> discounts) {
        this.discounts.clear();
        this.discounts.addAll(discounts);
        notifyDataSetChanged();
    }

    public interface OnDiscountSelectedListener {
        void onDiscountSelected(DiscountModel discount);
    }
}