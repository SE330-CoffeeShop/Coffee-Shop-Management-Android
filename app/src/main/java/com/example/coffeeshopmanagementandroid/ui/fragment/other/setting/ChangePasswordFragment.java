package com.example.coffeeshopmanagementandroid.ui.fragment.other.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.ui.MainActivity;
import com.example.coffeeshopmanagementandroid.ui.activity.AuthActivity;
import com.example.coffeeshopmanagementandroid.ui.component.BackButton;
import com.example.coffeeshopmanagementandroid.ui.viewmodel.AddressViewModel;
import com.example.coffeeshopmanagementandroid.ui.viewmodel.ChangePasswordViewModel;
import com.example.coffeeshopmanagementandroid.utils.NavigationUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChangePasswordFragment extends Fragment {
    protected BackButton backButton;
    private TextInputLayout newPasswordInputLayout;
    private TextInputLayout oldPasswordInputLayout;
    private TextInputLayout confirmPasswordInputLayout;
    private ChangePasswordViewModel changePasswordViewModel;
    private MaterialButton btnConfirm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changePasswordViewModel = new ViewModelProvider(requireActivity()).get(ChangePasswordViewModel.class);
        initBackButton(view);
        initViews(view);
    }

    private void initBackButton(View view) {
        backButton = view.findViewById(R.id.back_button);
        if (backButton != null) {
            backButton.setOnClickListener(v -> handleBackPressed());
        }
    }

    protected void initViews(View view) {
        oldPasswordInputLayout = view.findViewById(R.id.oldPasswordInputLayout);
        newPasswordInputLayout = view.findViewById(R.id.newPasswordInputLayout);
        confirmPasswordInputLayout = view.findViewById(R.id.confirmPasswordInputLayout);
        btnConfirm = view.findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(v -> {
            String oldPassword = oldPasswordInputLayout.getEditText().getText().toString();
            String newPassword = newPasswordInputLayout.getEditText().getText().toString();
            String confirmPassword = confirmPasswordInputLayout.getEditText().getText().toString();

            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(getContext(), "New password and confirm password do not match", Toast.LENGTH_SHORT).show();
            } else {
                changePasswordViewModel.changePassword(oldPassword, newPassword, confirmPassword);
            }
        });
        changePasswordViewModel.changePasswordSuccess.observe(getViewLifecycleOwner(), success -> {
            if (success != null && success) {
                Toast.makeText(getContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                handleLogout();
            } else if (success != null) {
                Toast.makeText(getContext(), "Failed to change password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleBackPressed() {
        if (isAdded()) {
            NavHostFragment.findNavController(this).navigateUp();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideBottomNavigation();
        }
    }

    private void handleLogout() {
//        logoutViewModel.logout();
        SharedPreferences prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        prefs.edit().remove("id").remove("role").remove("access_token").remove("refresh_token").putBoolean("is_logged_in", false).apply();

        Intent intent = new Intent(requireContext(), AuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showBottomNavigation();
        }
    }
}
