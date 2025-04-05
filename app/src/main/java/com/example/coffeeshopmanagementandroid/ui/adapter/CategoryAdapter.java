package com.example.coffeeshopmanagementandroid.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.domain.model.CategoryModel;
import com.example.coffeeshopmanagementandroid.ui.component.CategoryChipButton;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<CategoryModel> categoryList;
    private int selectedPosition = -1;

    public CategoryAdapter(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryChipButton categoryChipButton = new CategoryChipButton(parent.getContext());
        return new ViewHolder(categoryChipButton);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.categoryChipButton.setText(categoryList.get(position));
        holder.categoryChipButton.setSelected(position == selectedPosition);
        holder.categoryChipButton.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos == selectedPosition) {
                // Bỏ chọn nếu đang được chọn
                selectedPosition = -1;
                notifyItemChanged(pos);
            } else {
                // Chọn chip mới và bỏ chọn chip cũ (nếu có)
                int previous = selectedPosition;
                selectedPosition = pos;
                if (previous != -1) {
                    notifyItemChanged(previous);
                }
                notifyItemChanged(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CategoryChipButton categoryChipButton;

        public ViewHolder(CategoryChipButton itemView) {
            super(itemView);
            categoryChipButton = itemView;
        }
    }
}