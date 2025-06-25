package com.example.coffeeshopmanagementandroid.domain.repository;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.notification.response.NotificationResponse;

import java.util.List;

public interface NotificationRepository {
    BasePagingResponse<List<NotificationResponse>> getReceivedNotifications() throws Exception;
    void markNotificationAsRead(String id) throws Exception;
    void deleteNotification(String id) throws Exception;
}
