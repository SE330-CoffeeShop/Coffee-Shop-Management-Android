package com.example.coffeeshopmanagementandroid.domain.model;

import java.sql.Timestamp;

public class NotificationModel {
    private String notificationId;
    private String userSenderId;
    private String userReceiverId;
    private String notificationType;
    private Timestamp notificationDate;
    private String notificationContent;
    private boolean notificationIsRead;

    // Constructor đầy đủ tham số, bao gồm notificationContent
    public NotificationModel(String notificationId, String userSenderId, String userReceiverId,
                             String notificationType, Timestamp notificationDate, String notificationContent,
                             boolean notificationIsRead) {
        this.notificationId = notificationId;
        this.userSenderId = userSenderId;
        this.userReceiverId = userReceiverId;
        this.notificationType = notificationType;
        this.notificationDate = notificationDate;
        this.notificationContent = notificationContent;
        this.notificationIsRead = notificationIsRead;
    }

    // Getter và Setter

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserSenderId() {
        return userSenderId;
    }

    public void setUserSenderId(String userSenderId) {
        this.userSenderId = userSenderId;
    }

    public String getUserReceiverId() {
        return userReceiverId;
    }

    public void setUserReceiverId(String userReceiverId) {
        this.userReceiverId = userReceiverId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Timestamp getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public boolean isNotificationIsRead() {
        return notificationIsRead;
    }

    public void setNotificationIsRead(boolean notificationIsRead) {
        this.notificationIsRead = notificationIsRead;
    }
}
