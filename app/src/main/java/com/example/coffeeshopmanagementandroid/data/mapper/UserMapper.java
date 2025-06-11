package com.example.coffeeshopmanagementandroid.data.mapper;

import android.util.Log;

import com.example.coffeeshopmanagementandroid.data.dto.user.response.UserResponse;
import com.example.coffeeshopmanagementandroid.domain.model.UserModel;

public class UserMapper {
    public static UserModel mapUserResponseToUserDomain(UserResponse userResponse) {
        UserModel user = new UserModel();
        Log.d("UserMapper", "Mapping user response to domain: " + (userResponse == null ? "null" : "not null"));
        user.setUserId(userResponse.getId());
        user.setUserName(userResponse.getName());
        user.setUserLastName(userResponse.getLastName());
        user.setUserGender(userResponse.getGender());
        user.setUserEmail(userResponse.getEmail());
        user.setUserPhone(userResponse.getPhoneNumber());
        user.setUserAvatar(userResponse.getAvatar());
        user.setUserBirthday(userResponse.getBirthday());
        return user;
    }
}
