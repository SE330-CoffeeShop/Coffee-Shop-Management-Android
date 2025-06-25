package com.example.coffeeshopmanagementandroid.domain.usecase;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.notification.response.NotificationResponse;
import com.example.coffeeshopmanagementandroid.domain.repository.NotificationRepository;

import java.util.List;

public class NotificationUseCase {
    private final NotificationRepository notificationRepository;
    public NotificationUseCase(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public BasePagingResponse<List<NotificationResponse>> getReceivedNotifications() throws Exception {
        return notificationRepository.getReceivedNotifications();
    }
    public void markNotificationAsRead(String id) throws Exception {
        notificationRepository.markNotificationAsRead(id);
    }
    public void deleteNotification(String id) throws Exception {
        notificationRepository.deleteNotification(id);
    }
}
