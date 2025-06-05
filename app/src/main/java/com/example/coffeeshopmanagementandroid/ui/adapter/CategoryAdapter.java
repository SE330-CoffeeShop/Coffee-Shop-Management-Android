package com.example.coffeeshopmanagementandroid.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.ui.component.CategoryChipButton;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<CategoryModel> categories;
    private int selectedPosition = -1;
    private OnCategorySelectedListener onCategorySelectedListener;

    public CategoryAdapter(List<CategoryModel> categoryList) {
        this.categories = categoryList;
    }

    // Interface để thông báo khi category được chọn
    public interface OnCategorySelectedListener {
        void onCategorySelected(String categoryId);
    }

    public void setOnCategorySelectedListener(OnCategorySelectedListener listener) {
        this.onCategorySelectedListener = listener;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryChipButton categoryChipButton = new CategoryChipButton(parent.getContext());
        return new ViewHolder(categoryChipButton);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CategoryModel category = categories.get(position);
        holder.categoryChipButton.setText(categories.get(position));
        holder.categoryChipButton.setSelected(position == selectedPosition);
        holder.categoryChipButton.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos == selectedPosition) {
                // Bỏ chọn nếu đang được chọn
                selectedPosition = -1;
                notifyItemChanged(pos);
                if (onCategorySelectedListener != null) {
                    onCategorySelectedListener.onCategorySelected(null);
                }
            } else {
                // Chọn category mới
                int previous = selectedPosition;
                selectedPosition = pos;
                if (previous != -1) {
                    notifyItemChanged(previous);
                }
                notifyItemChanged(pos);
                if (onCategorySelectedListener != null) {
                    onCategorySelectedListener.onCategorySelected(category.getCategoryId()); // Gửi categoryId
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CategoryChipButton categoryChipButton;

        public ViewHolder(CategoryChipButton itemView) {
            super(itemView);
            categoryChipButton = itemView;
        }
    }
}