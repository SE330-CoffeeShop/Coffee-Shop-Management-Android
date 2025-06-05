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
import com.example.coffeeshopmanagementandroid.domain.model.PaymentMethodModel;

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {
    private final List<PaymentMethodModel> paymentMethods;
    private int selectedPosition = -1;
    private final OnPaymentSelectedListener listener;

    public PaymentMethodAdapter(List<PaymentMethodModel> paymentMethods, int defaultPosition, OnPaymentSelectedListener listener) {
        this.paymentMethods = paymentMethods;
        this.selectedPosition = defaultPosition;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_method_item_layout, parent, false);
        return new PaymentMethodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {
        PaymentMethodModel method = paymentMethods.get(position);
        holder.bind(method, position);
    }

    @Override
    public int getItemCount() {
        return paymentMethods.size();
    }

    public PaymentMethodModel getSelectedPaymentMethod() {
        if (selectedPosition != -1) {
            return paymentMethods.get(selectedPosition);
        }
        return null;
    }

    public class PaymentMethodViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivPaymentIcon;
        private final TextView tvPaymentMethod;
        private final RadioButton rbPaymentMethod;

        public PaymentMethodViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPaymentIcon = itemView.findViewById(R.id.ivPaymentIcon);
            tvPaymentMethod = itemView.findViewById(R.id.tvPaymentMethod);
            rbPaymentMethod = itemView.findViewById(R.id.rbPaymentMethod);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && selectedPosition != position) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                    if (listener != null) {
                        listener.onPaymentSelected(paymentMethods.get(position));
                    }
                }
            });
        }

        public void bind(PaymentMethodModel method, int position) {
            int iconResId = getIconResId(method.getPaymentMethodType());
            ivPaymentIcon.setImageResource(iconResId);
            tvPaymentMethod.setText(method.getPaymentMethodType());
            rbPaymentMethod.setChecked(position == selectedPosition);
        }

        private int getIconResId(String type) {
            switch (type) {
                case "CASH":
                    return R.drawable.cash_icon;
                case "BANK_CARD":
                    return R.drawable.card_icon;
                case "MOMO":
                    return R.drawable.momo_icon;
                case "ZALOPAY":
                    return R.drawable.zalo_pay_icon;
                default:
                    return R.drawable.cash_icon;
            }
        }
    }

    public interface OnPaymentSelectedListener {
        void onPaymentSelected(PaymentMethodModel paymentMethod);
    }
}