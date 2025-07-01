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
import com.example.coffeeshopmanagementandroid.ui.viewmodel.UpdateAddressViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class UpdateAddressFragment extends BaseOtherFragment {
    private BackButton backButton;
    private AutoCompleteTextView autoCompleteCity;
    private AutoCompleteTextView autoCompleteDistrict;
    private TextInputEditText detailAddress;
    private SwitchCompat switchSetDefault;
    private MaterialButton btnDeleteAddress;
    private MaterialButton btnComplete;
    private UpdateAddressViewModel updateAddressViewModel;

    private final String[] cities = {"TP. Hồ Chí Minh", "Hà Nội"};

    private final String[] hcmDistricts = {
            "Quận 1", "Quận 3", "Quận 5", "Quận 7", "Quận 10",
            "Thủ Đức", "Bình Thạnh", "Tân Bình", "Gò Vấp"
    };

    private final String[] hanoiDistricts = {
            "Ba Đình", "Hoàn Kiếm", "Đống Đa", "Thanh Xuân", "Cầu Giấy",
            "Long Biên", "Hai Bà Trưng", "Tây Hồ", "Nam Từ Liêm"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateAddressViewModel = new ViewModelProvider(requireActivity()).get(UpdateAddressViewModel.class);
        initViews(view);
        loadDataFromBundle();
    }

    protected int getLayoutResId() {
        return R.layout.fragment_update_address;
    }

    @Override
    protected void initViews(View view) {
        backButton = view.findViewById(R.id.back_button);
        autoCompleteCity = view.findViewById(R.id.autoCompleteCity);
        autoCompleteDistrict = view.findViewById(R.id.autoCompleteDistrict);
        detailAddress = view.findViewById(R.id.detailAddress);
        switchSetDefault = view.findViewById(R.id.switchSetDefault);
        btnDeleteAddress = view.findViewById(R.id.btnDeleteAddress);
        btnComplete = view.findViewById(R.id.btnComplete);

        autoCompleteCity.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, cities));
        autoCompleteCity.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedCity = (String) parent.getItemAtPosition(position);
            updateDistrictsByCity(selectedCity);
        });

        btnDeleteAddress.setOnClickListener(v -> {
            String addressId = getArguments() != null ? getArguments().getString("addressId") : null;
            if (addressId != null) {
                updateAddressViewModel.deleteAddress(addressId);
                Toast.makeText(getContext(), "Address deleted successfully", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(this).popBackStack();
            } else {
                Toast.makeText(getContext(), "No address ID found to delete", Toast.LENGTH_SHORT).show();
            }
        });

        btnComplete.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Complete", Toast.LENGTH_SHORT).show();
            String addressLine = detailAddress.getText().toString().trim();
            String addressCity = autoCompleteCity.getText().toString().trim();
            String addressDistrict = autoCompleteDistrict.getText().toString().trim();
            Boolean addressIsDefault = switchSetDefault.isChecked();

            if (addressLine.isEmpty() || addressCity.isEmpty() || addressDistrict.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    String addressId = getArguments() != null ? getArguments().getString("addressId") : null;
                    updateAddressViewModel.updateAddress(addressId, addressLine, addressCity, addressDistrict, addressIsDefault);
                    Toast.makeText(getContext(), "Address added successfully", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(this).popBackStack();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Failed to add address: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (backButton != null) {
            backButton.setOnClickListener(v -> handleBackPressed());
        }
    }

    private void updateDistrictsByCity(String city) {
        ArrayAdapter<String> districtAdapter;
        String firstDistrict = "";

        if ("TP. Hồ Chí Minh".equals(city)) {
            districtAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, hcmDistricts);
            firstDistrict = hcmDistricts[0];
        } else if ("Hà Nội".equals(city)) {
            districtAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, hanoiDistricts);
            firstDistrict = hanoiDistricts[0];
        } else {
            districtAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, new String[]{});
        }

        autoCompleteDistrict.setAdapter(districtAdapter);
        autoCompleteDistrict.setText(firstDistrict, false); // set quận đầu tiên
    }

    private void loadDataFromBundle() {
        Bundle args = getArguments();
        if (args != null) {
            String addressId = args.getString("addressId");
            String addressLine = args.getString("addressLine");
            String addressDistrict = args.getString("addressDistrict");
            String addressCity = args.getString("addressCity");
            boolean isDefault = args.getBoolean("isDefault", false);

            if (addressCity != null) {
                autoCompleteCity.setText(addressCity, false); // Set thành phố
                updateDistrictsByCity(addressCity); // Update đúng danh sách quận
            }

            if (addressDistrict != null) {
                autoCompleteDistrict.setText(addressDistrict, false); // Set quận theo giá trị truyền vào
            }

            if (addressLine != null) {
                detailAddress.setText(addressLine);
            }

            switchSetDefault.setChecked(isDefault);

            Toast.makeText(getContext(), "Đang chỉnh sửa địa chỉ: " + addressLine, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Không có dữ liệu địa chỉ", Toast.LENGTH_SHORT).show();
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
}
