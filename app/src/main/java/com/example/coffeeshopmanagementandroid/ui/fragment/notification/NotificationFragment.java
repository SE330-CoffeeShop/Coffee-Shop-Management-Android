package com.example.coffeeshopmanagementandroid.ui.fragment.notification;

import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.AddressModel;
import com.example.coffeeshopmanagementandroid.domain.model.NotificationModel;
import com.example.coffeeshopmanagementandroid.ui.adapter.AddressAdapter;
import com.example.coffeeshopmanagementandroid.ui.adapter.NotificationAdapter;
import com.example.coffeeshopmanagementandroid.ui.component.BackButton;
import com.example.coffeeshopmanagementandroid.ui.fragment.other.BaseOtherFragment;
import com.example.coffeeshopmanagementandroid.utils.SpaceItemDecoration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends BaseOtherFragment {

    private RecyclerView notificationRecyclerView;
    private NotificationAdapter notificationAdapter;
    private NavController navController;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_notification;
    }

    @Override
    protected void initViews(View view) {
        // Initialize any additional views specific to NotificationFragment
        setUpRecyclerView(view);
        navController = NavHostFragment.findNavController(this);
    }

    private void setUpRecyclerView(View view) {
        List<NotificationModel> notifications = List.of(
                new NotificationModel("N001", "User123", "User456", "ORDER_STATUS", new Timestamp(System.currentTimeMillis() - 3600_000), "Đơn hàng #12345 của bạn đã được giao thành công.", false),
                new NotificationModel("N002", "Admin", "User456", "PROMOTION", new Timestamp(System.currentTimeMillis() - 86_400_000), "Ưu đãi giảm giá 20% cho đơn hàng đầu tiên của bạn!", true),
                new NotificationModel("N003", "System", "User456", "SECURITY", new Timestamp(System.currentTimeMillis() - 300_000), "Có đăng nhập mới từ thiết bị lạ vào tài khoản của bạn.", false),
                new NotificationModel("N001", "User123", "User456", "ORDER_STATUS", new Timestamp(System.currentTimeMillis() - 3600_000), "Đơn hàng #12345 của bạn đã được giao thành công.", false),
                new NotificationModel("N002", "Admin", "User456", "PROMOTION", new Timestamp(System.currentTimeMillis() - 86_400_000), "Ưu đãi giảm giá 20% cho đơn hàng đầu tiên của bạn!", true),
                new NotificationModel("N003", "System", "User456", "SECURITY", new Timestamp(System.currentTimeMillis() - 300_000), "Có đăng nhập mới từ thiết bị lạ vào tài khoản của bạn.", false),
                new NotificationModel("N001", "User123", "User456", "ORDER_STATUS", new Timestamp(System.currentTimeMillis() - 3600_000), "Đơn hàng #12345 của bạn đã được giao thành công.", false),
                new NotificationModel("N002", "Admin", "User456", "PROMOTION", new Timestamp(System.currentTimeMillis() - 86_400_000), "Ưu đãi giảm giá 20% cho đơn hàng đầu tiên của bạn!", true),
                new NotificationModel("N003", "System", "User456", "SECURITY", new Timestamp(System.currentTimeMillis() - 300_000), "Có đăng nhập mới từ thiết bị lạ vào tài khoản của bạn.", false),
                new NotificationModel("N001", "User123", "User456", "ORDER_STATUS", new Timestamp(System.currentTimeMillis() - 3600_000), "Đơn hàng #12345 của bạn đã được giao thành công.", false),
                new NotificationModel("N002", "Admin", "User456", "PROMOTION", new Timestamp(System.currentTimeMillis() - 86_400_000), "Ưu đãi giảm giá 20% cho đơn hàng đầu tiên của bạn!", true),
                new NotificationModel("N003", "System", "User456", "SECURITY", new Timestamp(System.currentTimeMillis() - 300_000), "Có đăng nhập mới từ thiết bị lạ vào tài khoản của bạn.", false)
        );


        notificationRecyclerView = view.findViewById(R.id.notificationRecyclerView);
        notificationAdapter = new NotificationAdapter(notifications, notification -> {
            Toast.makeText(getContext(), "Clicked notification: " + notification.getNotificationContent(), Toast.LENGTH_SHORT).show();
        });

        notificationRecyclerView.setAdapter(notificationAdapter);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int marginTop = getResources().getDimensionPixelOffset(R.dimen.vertical_spacing);
        notificationRecyclerView.addItemDecoration(new SpaceItemDecoration().setTop(marginTop));
    }
}