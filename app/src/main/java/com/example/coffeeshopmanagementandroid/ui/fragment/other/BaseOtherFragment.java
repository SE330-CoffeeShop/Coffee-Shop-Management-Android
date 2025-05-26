package com.example.coffeeshopmanagementandroid.ui.fragment.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.ui.MainActivity;
import com.example.coffeeshopmanagementandroid.ui.component.BackButton;

public abstract class BaseOtherFragment extends Fragment {
    protected BackButton backButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        initViews(view);
        initBackButton(view);
        return view;
    }

    private void initBackButton(View view) {
        backButton = view.findViewById(R.id.back_button);
        if (backButton != null) {
            backButton.setOnClickListener(v -> handleBackPressed());
        }
    }

    protected void handleBackPressed() {
        if (isAdded()) {
            requireActivity().onBackPressed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideBottomNavigation();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showBottomNavigation();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        backButton = null;
    }

    protected abstract int getLayoutResId();

    protected abstract void initViews(View view);
}