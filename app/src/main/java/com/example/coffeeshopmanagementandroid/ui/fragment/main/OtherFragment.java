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
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OtherFragment extends Fragment {

    private OtherButton btnProfile;
    private OtherButton btnAddress;
    private OtherButton btnSetting;
    private OtherButton btnNotification;

    private NavController navController;

    public OtherFragment() {
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
        checkAndFixNavState();
        Log.d("OtherFragment", "NavController initialized in onViewCreated, current destination: " +
                (navController.getCurrentDestination() != null ? navController.getCurrentDestination().getId() : "null"));

        btnProfile = view.findViewById(R.id.cBtnProfileUser);
        btnAddress = view.findViewById(R.id.cBtnAddress);
        btnSetting = view.findViewById(R.id.cBtnSetting);
        btnNotification = view.findViewById(R.id.cBtnNotification);

        setupListeners();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null) {
            navController = NavHostFragment.findNavController(this);
            checkAndFixNavState();
            Log.d("OtherFragment", "NavController initialized in onResume, current destination: " +
                    (navController.getCurrentDestination() != null ? navController.getCurrentDestination().getId() : "null"));

            btnProfile = getView().findViewById(R.id.cBtnProfileUser);
            btnAddress = getView().findViewById(R.id.cBtnAddress);
            btnSetting = getView().findViewById(R.id.cBtnSetting);
            btnNotification = getView().findViewById(R.id.cBtnNotification);

            setupListeners();
        } else {
            Log.e("OtherFragment", "getView() is null in onResume");
        }
    }

    private void checkAndFixNavState() {
        if (navController.getCurrentDestination() != null &&
                navController.getCurrentDestination().getId() != R.id.otherFragment) {
            Log.w("OtherFragment", "Incorrect destination detected: " +
                    navController.getCurrentDestination().getId() + ". Attempting to fix.");
            // Đảm bảo back stack được cập nhật đúng
            navController.popBackStack(R.id.otherFragment, false);
        }
    }

    private void setupListeners() {
        if (btnProfile != null) {
            btnProfile.setOnClickListener(v -> navigateToProfileFragment());
            Log.d("OtherFragment", "btnProfile listener set");
        } else {
            Log.e("OtherFragment", "btnProfile is null");
        }
        if (btnAddress != null) {
            btnAddress.setOnClickListener(v -> navigateToAddressFragment());
            Log.d("OtherFragment", "btnAddress listener set");
        } else {
            Log.e("OtherFragment", "btnAddress is null");
        }
        if (btnSetting != null) {
            btnSetting.setOnClickListener(v -> navigateToSettingFragment());
            Log.d("OtherFragment", "btnSetting listener set");
        } else {
            Log.e("OtherFragment", "btnSetting is null");
        }
        if (btnNotification != null) {
            btnNotification.setOnClickListener(v -> navigatetoNotificationFragment());
            Log.d("OtherFragment", "btnNotification listener set");
        } else {
            Log.e("OtherFragment", "btnNotification is null");
        }
    }

    public void navigateToProfileFragment() {
        if (navController != null && navController.getCurrentDestination() != null) {
            if (navController.getCurrentDestination().getId() == R.id.otherFragment) {
                try {
                    Log.d("OtherFragment", "Attempting to navigate to ProfileFragment from otherFragment");
                    navController.navigate(R.id.action_otherFragment_to_profileFragment);
                    Log.d("OtherFragment", "Navigation to ProfileFragment successful");
                } catch (Exception e) {
                    Log.e("OtherFragment", "Navigation failed: " + e.getMessage());
                }
            } else {
                Log.e("OtherFragment", "Cannot navigate to ProfileFragment, current destination is: " +
                        navController.getCurrentDestination().getId());
            }
        } else {
            Log.e("OtherFragment", "NavController or current destination is null");
        }
    }

    public void navigateToAddressFragment() {
        if (navController != null && navController.getCurrentDestination() != null) {
            if (navController.getCurrentDestination().getId() == R.id.otherFragment) {
                try {
                    Log.d("OtherFragment", "Attempting to navigate to AddressFragment from otherFragment");
                    navController.navigate(R.id.action_otherFragment_to_addressFragment);
                    Log.d("OtherFragment", "Navigation to AddressFragment successful");
                } catch (Exception e) {
                    Log.e("OtherFragment", "Navigation failed: " + e.getMessage());
                }
            } else {
                Log.e("OtherFragment", "Cannot navigate to AddressFragment, current destination is: " +
                        navController.getCurrentDestination().getId());
            }
        } else {
            Log.e("OtherFragment", "NavController or current destination is null");
        }
    }

    public void navigateToSettingFragment() {
        if (navController != null && navController.getCurrentDestination() != null) {
            if (navController.getCurrentDestination().getId() == R.id.otherFragment) {
                try {
                    Log.d("OtherFragment", "Attempting to navigate to SettingFragment from otherFragment");
                    navController.navigate(R.id.action_otherFragment_to_settingFragment);
                    Log.d("OtherFragment", "Navigation to SettingFragment successful");
                } catch (Exception e) {
                    Log.e("OtherFragment", "Navigation failed: " + e.getMessage());
                }
            } else {
                Log.e("OtherFragment", "Cannot navigate to SettingFragment, current destination is: " +
                        navController.getCurrentDestination().getId());
            }
        } else {
            Log.e("OtherFragment", "NavController or current destination is null");
        }
    }

    public void navigatetoNotificationFragment() {
        if (navController != null && navController.getCurrentDestination() != null) {
            if (navController.getCurrentDestination().getId() == R.id.otherFragment) {
                try {
                    Log.d("OtherFragment", "Attempting to navigate to NotificationFragment from otherFragment");
                    navController.navigate(R.id.action_otherFragment_to_notificationFragment);
                    Log.d("OtherFragment", "Navigation to NotificationFragment successful");
                } catch (Exception e) {
                    Log.e("OtherFragment", "Navigation failed: " + e.getMessage());
                }
            } else {
                Log.e("OtherFragment", "Cannot navigate to NotificationFragment, current destination is: " +
                        navController.getCurrentDestination().getId());
            }
        } else {
            Log.e("OtherFragment", "NavController or current destination is null");
        }
    }
}