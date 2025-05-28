package com.example.coffeeshopmanagementandroid.ui.fragment.main;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.ui.component.OtherButton;
import com.example.coffeeshopmanagementandroid.utils.NavigationUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OtherFragment extends Fragment {

    private OtherButton btnProfile;
    private OtherButton btnAddress;
    private OtherButton btnSetting;
    private OtherButton btnNotification;

    private NavController navController;

    public OtherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(this);

        NavigationUtils.checkAndFixNavState(navController, R.id.otherFragment, "OtherFragment");

        btnProfile = view.findViewById(R.id.cBtnProfileUser);
        btnAddress = view.findViewById(R.id.cBtnAddress);
        btnSetting = view.findViewById(R.id.cBtnSetting);
        btnNotification = view.findViewById(R.id.cBtnNotification);

        setupListeners();
    }

    private void setupListeners() {
        btnProfile.setOnClickListener(v ->
                NavigationUtils.safeNavigate(navController,
                        R.id.otherFragment,
                        R.id.action_otherFragment_to_profileFragment,
                        "ProfileFragment",
                        "OtherFragment"));

        btnAddress.setOnClickListener(v ->
                NavigationUtils.safeNavigate(navController,
                        R.id.otherFragment,
                        R.id.action_otherFragment_to_addressFragment,
                        "AddressFragment",
                        "OtherFragment"));

        btnSetting.setOnClickListener(v ->
                NavigationUtils.safeNavigate(navController,
                        R.id.otherFragment,
                        R.id.action_otherFragment_to_settingFragment,
                        "SettingFragment",
                        "OtherFragment"));

        btnNotification.setOnClickListener(v ->
                NavigationUtils.safeNavigate(navController,
                        R.id.otherFragment,
                        R.id.action_otherFragment_to_notificationFragment,
                        "NotificationFragment",
                        "OtherFragment"));
    }
}
