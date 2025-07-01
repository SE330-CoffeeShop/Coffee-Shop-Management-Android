package com.example.coffeeshopmanagementandroid.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeshopmanagementandroid.data.dto.auth.request.ChangePasswordRequest;
import com.example.coffeeshopmanagementandroid.domain.usecase.AuthUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ChangePasswordViewModel extends ViewModel {
    private AuthUseCase authUseCase;
    private final MutableLiveData<Boolean> _changePasswordSuccess = new MutableLiveData<>();
    public LiveData<Boolean> changePasswordSuccess = _changePasswordSuccess;
    @Inject()
    public ChangePasswordViewModel(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    public void changePassword(String oldPassword, String newPassword, String confirmPassword) {
        new Thread(() -> {
            try {
                ChangePasswordRequest request = new ChangePasswordRequest(oldPassword, newPassword, confirmPassword);
                authUseCase.changePassword(request);
                _changePasswordSuccess.postValue(true);  // báo thành công
            } catch (Exception e) {
                e.printStackTrace();
                _changePasswordSuccess.postValue(false); // báo thất bại
            }
        }).start();
    }
}
