package com.example.coffeeshopmanagementandroid.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;

public class CategoryChipButton extends LinearLayout {
    private TextView categoryChipTextView;
    private LinearLayout chipContainer;
    private boolean isSelected;

    private int normalBackgroundcolor;
    private int normalTextcolor;
    private int selectedBackgroundcolor;
    private int selectedTextcolor;

    public CategoryChipButton(Context context) {
        super(context);
        init(context);
    }

    public CategoryChipButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CategoryChipButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.chip_category_layout, this, true);
        categoryChipTextView = findViewById(R.id.categoryChipTextView);
        chipContainer = findViewById(R.id.chipContainer);

        normalBackgroundcolor = context.getResources().getColor(R.color.white);
        normalTextcolor = context.getResources().getColor(R.color.black);
        selectedBackgroundcolor = context.getResources().getColor(R.color.primary_400);
        selectedTextcolor = context.getResources().getColor(R.color.white);

        updateAppearance();
        // Xóa dòng này: setOnClickListener(v -> toggleSelection());
    }

    private void updateAppearance() {
        if (isSelected) {
            chipContainer.setBackgroundColor(selectedBackgroundcolor);
            categoryChipTextView.setTextColor(selectedTextcolor);
        } else {
            chipContainer.setBackgroundColor(normalBackgroundcolor);
            categoryChipTextView.setTextColor(normalTextcolor);
        }
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        updateAppearance();
    }

    public void setText(CategoryModel categoryModel) {
        categoryChipTextView.setText(categoryModel.getCategoryName());
    }
}