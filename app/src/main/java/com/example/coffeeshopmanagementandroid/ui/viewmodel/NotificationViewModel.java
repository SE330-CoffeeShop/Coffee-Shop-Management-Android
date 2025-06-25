package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.data.dto.BasePagingResponse;
import com.example.coffeeshopmanagementandroid.data.dto.notification.response.NotificationResponse;
import com.example.coffeeshopmanagementandroid.data.mapper.NotificationMapper;
import com.example.coffeeshopmanagementandroid.domain.model.notification.NotificationModel;
import com.example.coffeeshopmanagementandroid.domain.usecase.NotificationUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NotificationViewModel extends ViewModel {
    private final NotificationUseCase notificationUseCase;
    private final MutableLiveData<List<NotificationModel>> notificationModelsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    @Inject
    public NotificationViewModel(NotificationUseCase notificationUseCase) {
        this.notificationUseCase = notificationUseCase;
    }

    public MutableLiveData<List<NotificationModel>> getNotificationModelsLiveData() {
        return notificationModelsLiveData;
    }

    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void fetchNotifications() {
        new Thread(() -> {
            try {
                BasePagingResponse<List<NotificationResponse>> response = notificationUseCase.getReceivedNotifications();
                List<NotificationModel> models = NotificationMapper.mapToNotificationModelList(response.getData());
                notificationModelsLiveData.postValue(models);
            } catch (Exception e) {
                errorLiveData.postValue(e.getMessage());
            }
        }).start();
    }

    public void markNotificationAsRead(String notificationId) {
        new Thread(() -> {
            try {
                notificationUseCase.markNotificationAsRead(notificationId);
                // Optionally, refresh notifications or update the read status in LiveData
            } catch (Exception e) {
                errorLiveData.postValue(e.getMessage());
            }
        }).start();
    }
}