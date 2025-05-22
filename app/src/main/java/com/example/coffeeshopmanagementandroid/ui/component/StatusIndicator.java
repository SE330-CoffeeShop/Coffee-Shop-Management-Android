package com.example.coffeeshopmanagementandroid.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.coffeeshopmanagementandroid.R;

public class StatusIndicator extends LinearLayout {
    private View viewIndicator;
    private TextView tvStatus;

    public StatusIndicator(Context context) {
        super(context);
        init(context);
    }

    public StatusIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.status_indicator, this);
        viewIndicator = findViewById(R.id.viewIndicator);
        this.tvStatus = findViewById(R.id.tvStatus);
    }

    public void setStatus(String status) {
        this.tvStatus.setText(status);
        switch (status.toLowerCase()) {
            case "đã giao":
                this.viewIndicator.setBackgroundResource(R.drawable.bg_status_order_successful);
                this.tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.primary_400));
                break;
            case "đã hủy":
                this.viewIndicator.setBackgroundResource(R.drawable.bg_status_order_cancel);
                this.tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.error_500));
                break;
            default:
                this.viewIndicator.setBackgroundResource(R.drawable.bg_status_order);
                this.tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.warning_500));
                break;
        }
    }
}
