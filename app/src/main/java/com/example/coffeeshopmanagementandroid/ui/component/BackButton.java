package com.example.coffeeshopmanagementandroid.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.widget.TextView;
import com.example.coffeeshopmanagementandroid.R; // Đổi thành package thực tế của bạn

public class BackButton extends LinearLayout {
    private Toolbar toolbar;
    private TextView backButtonText;

    public BackButton(Context context) {
        super(context);
        init(context);
    }

    public BackButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BackButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.back_button, this, true);
        toolbar = findViewById(R.id.toolbar);
        backButtonText = findViewById(R.id.back_button_text);

        toolbar.setNavigationOnClickListener(v -> {
            if (context instanceof FragmentActivity) {
                FragmentActivity activity = (FragmentActivity) context;
                if (!activity.getSupportFragmentManager().isStateSaved()) {
                    activity.getSupportFragmentManager().popBackStack();
                }
            }
        });
    }

    public void setText(String text) {
        backButtonText.setText(text);
    }

}
