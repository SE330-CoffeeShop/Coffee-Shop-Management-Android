package com.example.coffeeshopmanagementandroid.ui.fragment.other.address;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.address.AddressModel;
import com.example.coffeeshopmanagementandroid.ui.adapter.AddressAdapter;
import com.example.coffeeshopmanagementandroid.ui.adapter.ChooseAddressAdapter;
import com.example.coffeeshopmanagementandroid.ui.fragment.other.BaseOtherFragment;
import com.example.coffeeshopmanagementandroid.ui.viewmodel.AddressViewModel;
import com.example.coffeeshopmanagementandroid.ui.viewmodel.ConfirmOrderViewModel;
import com.example.coffeeshopmanagementandroid.utils.NavigationUtils;
import com.example.coffeeshopmanagementandroid.utils.SpaceItemDecoration;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddressFragment extends BaseOtherFragment {

    private RecyclerView addressRecyclerView;
    private MaterialButton btnAddAddress;
    private NavController navController;
    private AddressViewModel addressViewModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_address;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addressViewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);
        setupAddress();
        initViews(view);;
    }

    @Override
    protected void initViews(View view) {

        navController = NavHostFragment.findNavController(this);
        NavigationUtils.checkAndFixNavState(navController, R.id.addressFragment, "AddressFragment");
        btnAddAddress = view.findViewById(R.id.btnAddAddress);
        btnAddAddress.setOnClickListener(v -> {
            navigateToAddAddressFragment();
        });
        addressRecyclerView = view.findViewById(R.id.addressRecyclerView);
    }

    private void setupAddress() {
        addressViewModel.fetchAllAddresses();

        addressViewModel.getAddressesLiveData().observe(getViewLifecycleOwner(), addresses -> {
            if (addresses != null && !addresses.isEmpty()) {
                addressRecyclerView.setVisibility(View.VISIBLE);
                AddressAdapter addressAdapter = new AddressAdapter(addresses, this::navigateToUpdateAddress);
                addressRecyclerView.setAdapter(addressAdapter);
                addressRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                int marginTop = getResources().getDimensionPixelOffset(R.dimen.vertical_spacing);
                addressRecyclerView.addItemDecoration(new SpaceItemDecoration().setTop(marginTop));
            } else {
                addressRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    private void navigateToUpdateAddress(AddressModel address) {
        if (address != null) {
            Bundle args = new Bundle();
            args.putString("addressId", address.getAddressId());
            args.putString("addressLine", address.getAddressLine());
            args.putString("addressDistrict", address.getAddressDistrict());
            args.putString("addressCity", address.getAddressCity());

            NavigationUtils.safeNavigate(navController,
                    R.id.addressFragment,
                    R.id.action_addressFragment_to_updateAddressFragment,
                    "UpdateAddressFragment",
                    "AddressFragment",
                    args);
        } else {
            Log.e("AddressFragment", "AddressModel is null, cannot navigate to UpdateAddressFragment from recent");
        }
    }

    private void navigateToAddAddressFragment() {
        NavigationUtils.safeNavigate(navController,
                R.id.addressFragment,
                R.id.action_addressFragment_to_addAddressFragment,
                "AddAddressFragment",
                "AddressFragment",
                null);
    }
}