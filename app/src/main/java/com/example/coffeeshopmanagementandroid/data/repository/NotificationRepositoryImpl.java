package com.example.coffeeshopmanagementandroid.data.repository;

import com.example.coffeeshopmanagementandroid.data.api.NotificationService;
import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.notification.request.GetAllReceivedNotificationRequests;
import com.example.coffeeshopmanagementandroid.data.dto.notification.response.NotificationResponse;
import com.example.coffeeshopmanagementandroid.domain.repository.NotificationRepository;

import java.util.List;

public class NotificationRepositoryImpl implements NotificationRepository {
    private final NotificationService notificationService;

    public NotificationRepositoryImpl(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public BasePagingResponse<List<NotificationResponse>> getReceivedNotifications(GetAllReceivedNotificationRequests requests) throws Exception {
        try {
            return notificationService.getReceivedNotifications(requests.getPage(), requests.getLimit(), requests.getSortType().toString(), requests.getSortBy().toString()).execute().body();
        } catch (Exception e) {
            throw new Exception("Failed to fetch received notifications", e);
        }
    }

    @Override
    public void markNotificationAsRead(String id) throws Exception {
        try {
            notificationService.markNotificationAsRead(id).execute();
        } catch (Exception e) {
            throw new Exception("Failed to mark notification as read", e);
        }
    }

    @Override
    public void deleteNotification(String id) throws Exception {
        try {
            notificationService.deleteNotification(id).execute();
        } catch (Exception e) {
            throw new Exception("Failed to delete notification", e);
        }
    }
}
