package com.example.coffeeshopmanagementandroid.ui.fragment.other;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coffeeshopmanagementandroid.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfileFragment extends BaseOtherFragment {
    // Ảnh avatar
    private ImageButton customImageButton;

    // Họ tên
    private TextInputLayout lastNameInputLayout;
    private TextInputEditText editTextLastName;
    private TextInputLayout firstNameInputLayout;
    private TextInputEditText editTextFirstName;

    // Giới tính
    private TextInputLayout genderInputLayout;
    private AutoCompleteTextView autoCompleteGender;

    // Ngày sinh
    private TextInputLayout dateOfBirthInputLayout;
    private TextInputEditText editTextDateOfBirth;
    private Calendar calendar;

    // Số điện thoại
    private TextInputLayout phoneInputLayout;
    private TextInputEditText editTextPhoneNumber;
    private AutoCompleteTextView countryCodePicker;
    private ImageView flagImageView;

    // Email
    private TextInputLayout emailInputLayout;
    private TextInputEditText emailNumberEditText;

    // Button sửa
    private Button btnUpdateInformation;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initViews(@NonNull View view) {
        // Ảnh avatar
        customImageButton = view.findViewById(R.id.customImageButton);

        // Họ tên
        lastNameInputLayout = view.findViewById(R.id.lastNameInputLayout);
        editTextLastName = view.findViewById(R.id.editTextLastName);
        firstNameInputLayout = view.findViewById(R.id.firstNameInputLayout);
        editTextFirstName = view.findViewById(R.id.editTextFirstName);

        // Giới tính
        genderInputLayout = view.findViewById(R.id.genderInputLayout);
        autoCompleteGender = view.findViewById(R.id.autoCompleteGender);

        // Ngày sinh
        dateOfBirthInputLayout = view.findViewById(R.id.dateOfBirthInputLayout);
        editTextDateOfBirth = view.findViewById(R.id.editTextDateOfBirth);

        // Số điện thoại
        phoneInputLayout = view.findViewById(R.id.phoneInputLayout);
        editTextPhoneNumber = view.findViewById(R.id.phoneNumberEditText);
        countryCodePicker = view.findViewById(R.id.countryCodePicker);
        flagImageView = view.findViewById(R.id.flagImageView);

        // Email
        emailInputLayout = view.findViewById(R.id.emailInputLayout);
        emailNumberEditText = view.findViewById(R.id.emailNumberEditText);

        // Button sửa
        btnUpdateInformation = view.findViewById(R.id.btnUpdateInformation);

        // Khởi tạo calendar
        calendar = Calendar.getInstance();

        // Kiểm tra null để tránh crash
        if (customImageButton == null || lastNameInputLayout == null || editTextLastName == null ||
                firstNameInputLayout == null || editTextFirstName == null || genderInputLayout == null ||
                autoCompleteGender == null || dateOfBirthInputLayout == null || editTextDateOfBirth == null ||
                phoneInputLayout == null || editTextPhoneNumber == null || countryCodePicker == null ||
                flagImageView == null || emailInputLayout == null || emailNumberEditText == null ||
                btnUpdateInformation == null) {
            return;
        }

        // Xử lý sự kiện chọn ngày sinh
        editTextDateOfBirth.setOnClickListener(v -> showDatePickerDialog());
        dateOfBirthInputLayout.setEndIconOnClickListener(v -> showDatePickerDialog());

        // Thiết lập dropdown giới tính
        setupGenderPicker();

        // Thiết lập dropdown mã quốc gia
        setupCountryCodePicker();

        // Xử lý sự kiện cho button sửa
        btnUpdateInformation.setOnClickListener(v -> {
            setUpdateButtonClickListener();
        });

        // Xử lý sự kiện cho ảnh avatar
        customImageButton.setOnClickListener(v -> {
            setUpBtnUpdateInformation();
        });
    }

    private void setupCountryCodePicker() {
        String[] countryCodes = {"+84", "+1", "+44", "+91", "+61"};
        String[] countryNames = {"Vietnam", "USA", "UK", "India", "Australia"};
        int[] flags = {R.drawable.flag_vietnam_icon, R.drawable.flag_singapore_icon, R.drawable.flag_china_icon,
                R.drawable.flag_vietnam_icon, R.drawable.flag_singapore_icon};

        String[] displayItems = new String[countryCodes.length];
        for (int i = 0; i < countryCodes.length; i++) {
            displayItems[i] = countryCodes[i] + " - " + countryNames[i]; // Ví dụ: "+84 - Vietnam"
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.country_dropdown_item,
                R.id.countryText,
                displayItems
        );
        countryCodePicker.setAdapter(adapter);
        countryCodePicker.setThreshold(5); // Hiển thị dropdown ngay khi nhấn

        // Xử lý sự kiện nhấn để hiển thị dropdown
        countryCodePicker.setOnClickListener(v -> {
            countryCodePicker.showDropDown();
            countryCodePicker.requestFocus();
        });

        // Xử lý sự kiện focus để hiển thị dropdown
        countryCodePicker.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                countryCodePicker.showDropDown();
            }
        });

        // Xử lý sự kiện chọn mã quốc gia
        countryCodePicker.setOnItemClickListener((parent, itemView, position, id) -> {
            String selectedCode = countryCodes[position];
            countryCodePicker.setText(selectedCode);
            flagImageView.setImageResource(flags[position]);
            phoneInputLayout.setPrefixText(selectedCode + " ");
        });

        // Thiết lập giá trị mặc định
        countryCodePicker.setText(countryCodes[0]);
        flagImageView.setImageResource(flags[0]);
        phoneInputLayout.setPrefixText(countryCodes[0] + " ");
    }

    private void setupGenderPicker() {
        String[] genders = {"Nam", "Nữ", "Khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                genders
        );
        autoCompleteGender.setAdapter(adapter);
        autoCompleteGender.setThreshold(0);

        // Hiển thị dropdown khi nhấn
        autoCompleteGender.setOnClickListener(v -> {
            autoCompleteGender.showDropDown();
            autoCompleteGender.requestFocus();
        });

        autoCompleteGender.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                autoCompleteGender.showDropDown();
            }
        });
    }

    private void showDatePickerDialog() {
        // Lấy ngày hiện tại
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view1, selectedYear, selectedMonth, selectedDay) -> {
                    calendar.set(selectedYear, selectedMonth, selectedDay);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    editTextDateOfBirth.setText(sdf.format(calendar.getTime()));
                },
                year,
                month,
                day
        );

        // Giới hạn không chọn ngày trong tương lai
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    void setUpdateButtonClickListener() {
        Toast.makeText(requireContext(), "Button Update Information clicked", Toast.LENGTH_SHORT).show();
    }

    void setUpBtnUpdateInformation() {
        Toast.makeText(requireContext(), "Button Update Image clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Có thể thêm logic khởi tạo khác nếu cần
    }
}