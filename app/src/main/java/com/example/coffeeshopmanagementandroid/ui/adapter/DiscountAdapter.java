package com.example.coffeeshopmanagementandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.DiscountModel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder> {
    private final List<DiscountModel> discounts;
    private int selectedPosition = -1;
    private final OnDiscountSelectedListener listener;

    public DiscountAdapter(List<DiscountModel> discounts, OnDiscountSelectedListener listener) {
        this.discounts = discounts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_item_layout, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        DiscountModel discount = discounts.get(position);
        holder.bind(discount, position);
    }

    @Override
    public int getItemCount() {
        return discounts.size();
    }

    public DiscountModel getSelectedDiscount() {
        if (selectedPosition != -1) {
            return discounts.get(selectedPosition);
        }
        return null;
    }

    public class DiscountViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivDiscountImage;
        private final TextView tvDiscountName;
        private final TextView tvDiscountCondition;
        private final TextView tvDiscountExpiry;
        private final RadioButton rbDiscount;

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDiscountImage = itemView.findViewById(R.id.ivDiscountImage);
            tvDiscountName = itemView.findViewById(R.id.tvDiscountName);
            tvDiscountCondition = itemView.findViewById(R.id.tvDiscountCondition);
            tvDiscountExpiry = itemView.findViewById(R.id.tvDiscountExpiry);
            rbDiscount = itemView.findViewById(R.id.rbDiscount);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && selectedPosition != position && isDiscountApplicable(discounts.get(position))) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                    if (listener != null) {
                        listener.onDiscountSelected(discounts.get(position));
                    }
                }
            });
        }

        public void bind(DiscountModel discount, int position) {
            ivDiscountImage.setImageResource(R.drawable.discount_icon); // Thay bằng hình ảnh thực tế nếu cần
            tvDiscountName.setText(discount.getDiscountName());

            String condition;
            if ("MIN_ORDER".equals(discount.getDiscountType())) {
                condition = "Đơn tối thiểu: " + discount.getDiscountMinOrderValue() + "K";
            } else {
                condition = "Áp dụng cho sản phẩm cụ thể"; // Thay bằng logic thực tế nếu có danh sách sản phẩm
            }
            tvDiscountCondition.setText(condition);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            tvDiscountExpiry.setText("Hết hạn: " + sdf.format(discount.getDiscountEndDate()));
            rbDiscount.setChecked(position == selectedPosition);

            if (isDiscountApplicable(discount)) {
                itemView.setAlpha(1.0f);
                itemView.setEnabled(true);
            } else {
                itemView.setAlpha(0.5f);
                itemView.setEnabled(false);
            }
        }

        private boolean isDiscountApplicable(DiscountModel discount) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            return discount.getDiscountIsActive() && discount.getDiscountEndDate().after(now);
            // Thêm logic kiểm tra điều kiện khác nếu cần (ví dụ: đơn hàng tối thiểu)
        }
    }

    public interface OnDiscountSelectedListener {
        void onDiscountSelected(DiscountModel discount);
    }
}