package com.example.coffeeshopmanagementandroid.ui.fragment.other.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.ui.MainActivity;
import com.example.coffeeshopmanagementandroid.ui.component.BackButton;
import com.example.coffeeshopmanagementandroid.ui.fragment.other.BaseOtherFragment;
import com.example.coffeeshopmanagementandroid.ui.viewmodel.AddAddressViewModel;
import com.example.coffeeshopmanagementandroid.ui.viewmodel.AddressViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddAddressFragment extends BaseOtherFragment {
    private BackButton backButton;
    private AddAddressViewModel addAddressViewModel;
    private AutoCompleteTextView autoCompleteCity;
    private AutoCompleteTextView autoCompleteDistrict;
    private TextInputEditText detailAddress;
    private MaterialButton btnComplete;

    private SwitchCompat switchSetDefault;

    private final String[] cities = {"TP. Hồ Chí Minh", "Hà Nội"};

    private final String[] hcmDistricts = {
            "Quận 1", "Quận 3", "Quận 5", "Quận 7", "Quận 10",
            "Thủ Đức", "Bình Thạnh", "Tân Bình", "Gò Vấp"
    };

    private final String[] hanoiDistricts = {
            "Ba Đình", "Hoàn Kiếm", "Đống Đa", "Thanh Xuân", "Cầu Giấy",
            "Long Biên", "Hai Bà Trưng", "Tây Hồ", "Nam Từ Liêm"
    };

    protected int getLayoutResId() {
        return R.layout.fragment_add_address;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addAddressViewModel = new ViewModelProvider(requireActivity()).get(AddAddressViewModel.class);
        initViews(view);
    }

    @Override
    protected void initViews(View view) {
        backButton = view.findViewById(R.id.back_button);
        autoCompleteCity = view.findViewById(R.id.autoCompleteCity);
        autoCompleteDistrict = view.findViewById(R.id.autoCompleteDistrict);
        detailAddress = view.findViewById(R.id.detailAddress);
        btnComplete = view.findViewById(R.id.btnComplete);
        switchSetDefault = view.findViewById(R.id.switchSetDefault);

        String[] cities = {"TP. Hồ Chí Minh", "Hà Nội"};
        String[] hcmDistricts = {"Quận 1", "Quận 3", "Quận 5", "Quận 7", "Quận 10", "Thủ Đức", "Bình Thạnh", "Tân Bình", "Gò Vấp"};
        String[] hanoiDistricts = {"Ba Đình", "Hoàn Kiếm", "Đống Đa", "Thanh Xuân", "Cầu Giấy", "Long Biên", "Hai Bà Trưng", "Tây Hồ", "Nam Từ Liêm"};

        autoCompleteCity.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, cities));
        autoCompleteCity.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedCity = (String) parent.getItemAtPosition(position);
            ArrayAdapter<String> districtAdapter;

            if ("TP. Hồ Chí Minh".equals(selectedCity)) {
                districtAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, hcmDistricts);
            } else if ("Hà Nội".equals(selectedCity)) {
                districtAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, hanoiDistricts);
            } else {
                districtAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, new String[]{});
            }

            autoCompleteDistrict.setAdapter(districtAdapter);
            autoCompleteDistrict.setText("");
        });

        btnComplete.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Complete", Toast.LENGTH_SHORT).show();
            String addressLine = detailAddress.getText().toString().trim();
            String addressCity = autoCompleteCity.getText().toString().trim();
            String addressDistrict = autoCompleteDistrict.getText().toString().trim();
            Boolean addressIsDefault = switchSetDefault.isChecked();

            if (addressLine.isEmpty() || addressCity.isEmpty() || addressDistrict.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            } else {
                try {
                    addAddressViewModel.addAddress(addressLine, addressCity, addressDistrict, addressIsDefault);
                    Toast.makeText(getContext(), "Address added successfully", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(this).popBackStack();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Failed to add address: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}