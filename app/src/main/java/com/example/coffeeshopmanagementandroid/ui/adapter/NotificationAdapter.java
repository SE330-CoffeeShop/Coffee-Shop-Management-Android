package com.example.coffeeshopmanagementandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.AddressModel;
import com.example.coffeeshopmanagementandroid.domain.model.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<NotificationModel> notifications;
    private OnItemClickListener onItemClickListener;

    public NotificationAdapter(List<NotificationModel> notifications, OnItemClickListener onItemClickListener) {
        this.notifications = notifications;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_layout, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationModel notification = notifications.get(position);
        holder.bind(notification);
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivNotificationImage;
        private TextView tvNotificationLabel;
        private TextView tvNotificationContent;
        private ImageView ivReadStatus;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNotificationImage = itemView.findViewById(R.id.ivNotificationImage);
            tvNotificationLabel = itemView.findViewById(R.id.tvNotificationLabel);
            tvNotificationContent = itemView.findViewById(R.id.tvNotificationContent);
            ivReadStatus = itemView.findViewById(R.id.ivReadStatus);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(notifications.get(position));
                }
            });
        }

        public void bind(NotificationModel notification) {
            // Thiết lập hình ảnh
            ivNotificationImage.setImageResource(R.drawable.notification_image);

            // Thiết lập nội dung
            tvNotificationLabel.setText(notification.getNotificationType());
            tvNotificationContent.setText(notification.getNotificationContent());

            // Thiết lập icon trạng thái đọc/chưa đọc
            if (notification.isNotificationIsRead()) {
                ivReadStatus.setVisibility(View.GONE);
                itemView.setBackgroundResource(R.drawable.bg_notification_read);
            } else {
                ivReadStatus.setImageResource(R.drawable.unread_icon); // Icon chưa đọc
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NotificationModel notification);
    }
}