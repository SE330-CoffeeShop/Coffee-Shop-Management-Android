package com.example.coffeeshopmanagementandroid.ui.fragment.other.address;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.AddressModel;
import com.example.coffeeshopmanagementandroid.ui.adapter.AddressAdapter;
import com.example.coffeeshopmanagementandroid.ui.fragment.other.BaseOtherFragment;
import com.example.coffeeshopmanagementandroid.utils.NavigationUtils;
import com.example.coffeeshopmanagementandroid.utils.SpaceItemDecoration;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class AddressFragment extends BaseOtherFragment {

    private RecyclerView addressRecyclerView;
    private MaterialButton btnAddAddress;
    private NavController navController;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_address;
    }

    @Override
    protected void initViews(View view) {
        setupRecentlyAddress(view);
        setUpRecyclerView(view);
        navController = NavHostFragment.findNavController(this);
        NavigationUtils.checkAndFixNavState(navController, R.id.addressFragment, "AddressFragment");
        btnAddAddress = view.findViewById(R.id.btnAddAddress);
        btnAddAddress.setOnClickListener(v -> {
            navigateToAddAddressFragment();
        });
    }

    private void setUpRecyclerView(View view) {
        List<AddressModel> addresses = new ArrayList<>();
        addresses.add(new AddressModel("addr_001", "FEEL Coffee & Tee Express, 82 Đ. Vành Đai", "Đông Hoà, Dĩ An", "Bình Dương", true));
        addresses.add(new AddressModel("addr_002", "123 Lê Lợi", "Quận 1", "TP. Hồ Chí Minh", false));
        addresses.add(new AddressModel("addr_003", "456 Nguyễn Trãi", "Thanh Xuân", "Hà Nội", false));
        addresses.add(new AddressModel("addr_004", "789 Trần Hưng Đạo", "Hải Châu", "Đà Nẵng", false));
        addresses.add(new AddressModel("addr_005", "101 Phan Đình Phùng", "Ninh Kiều", "Cần Thơ", false));

        addressRecyclerView = view.findViewById(R.id.addressRecyclerView);
        AddressAdapter adapter = new AddressAdapter(addresses, this::navigateToUpdateAddress);
        addressRecyclerView.setAdapter(adapter);
        addressRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int marginTop = getResources().getDimensionPixelOffset(R.dimen.vertical_spacing);
        addressRecyclerView.addItemDecoration(new SpaceItemDecoration().setTop(marginTop));
    }

    private void setupRecentlyAddress(View view) {
        View recentlyAddress = view.findViewById(R.id.iRecentlyAddress);
        if (recentlyAddress != null) {
            TextView tvAddress = recentlyAddress.findViewById(R.id.tvRecentlyAddress);
            ImageView iconEditAddress = recentlyAddress.findViewById(R.id.iconEditRecentlyAddress);

            // Dummy data - chỗ này sử dụng API lấy Address của đơn gần nhất
            List<AddressModel> addresses = new ArrayList<>();
            addresses.add(new AddressModel("addr_001", "Hehehe & Tee Express, 82 Đ. Vành Đai", "Đông Hoà, Dĩ An", "Bình Dương", true));
            AddressModel recentAddress;
            recentAddress = addresses.get(0);

            if (recentAddress != null) {
                tvAddress.setText(recentAddress.getAddressLine() + "\n" + recentAddress.getAddressDistrict() + ", " + recentAddress.getAddressCity());
                recentlyAddress.setOnClickListener(v -> navigateToUpdateAddress(recentAddress));
            } else {
                tvAddress.setText("Không có địa chỉ gần đây");
                recentlyAddress.setVisibility(View.GONE);
            }
        }
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