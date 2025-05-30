package com.example.coffeeshopmanagementandroid.ui.fragment.discount;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.DiscountModel;
import com.example.coffeeshopmanagementandroid.ui.MainActivity;
import com.example.coffeeshopmanagementandroid.ui.adapter.DiscountAdapter;
import com.example.coffeeshopmanagementandroid.utils.SpaceItemDecoration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DiscountFragment extends Fragment {
    private RecyclerView discountRecyclerView;
    private NavController navController;
    private ImageButton backButton;
    private Button applyButton;
    private DiscountAdapter discountAdapter;
    private DiscountModel selectedDiscount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discount, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setupDiscounts();
    }

    private void initViews(View view) {
        navController = NavHostFragment.findNavController(this);
        discountRecyclerView = view.findViewById(R.id.discountRecyclerView);
        backButton = view.findViewById(R.id.backButtonToConfirmOrder);
        applyButton = view.findViewById(R.id.applyButton);

        backButton.setOnClickListener(v -> handleBackPressed());
        applyButton.setOnClickListener(v -> applyDiscount());
    }

    private void setupDiscounts() {
        List<DiscountModel> discounts = new ArrayList<>();

        discounts.add(new DiscountModel(
                "1",
                "Giảm 10%",
                "Đơn tối thiểu 100k",
                "MIN_ORDER",
                10.0,
                "DIS10",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis() + 86400000L),
                100,
                20,
                1,
                100000,
                true,
                "BR001"
        ));

        discounts.add(new DiscountModel(
                "2",
                "Giảm 20k",
                "Chọn sản phẩm áp dụng",
                "PRODUCT",
                20000.0,
                "DIS20K",
                new Timestamp(System.currentTimeMillis() - 86400000L),
                new Timestamp(System.currentTimeMillis() + 86400000L * 7),
                50,
                10,
                2,
                0,
                true,
                "BR002"
        ));

        discounts.add(new DiscountModel(
                "3",
                "Mua 1 tặng 1",
                "Áp dụng cho mặt hàng A",
                "PROMO",
                0.0,
                "MUA1TANG1",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis() + 604800000L),
                30,
                5,
                1,
                0,
                true,
                "BR003"
        ));

        discountAdapter = new DiscountAdapter(discounts, discount -> {
            this.selectedDiscount = discount;
        });

        discountRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        discountRecyclerView.setAdapter(discountAdapter);

        int marginTop = getResources().getDimensionPixelOffset(R.dimen.vertical_spacing);
        discountRecyclerView.addItemDecoration(new SpaceItemDecoration().setTop(marginTop));
    }

    private void applyDiscount() {
        Bundle result = new Bundle();
        if (selectedDiscount != null) {
            result.putParcelable("selectedDiscount", (Parcelable) selectedDiscount);
        } else {
            result.putString("noDiscount", "true");
        }
        getParentFragmentManager().setFragmentResult("discountResult", result);
        handleBackPressed();
    }

    private void handleBackPressed() {
        navController.popBackStack();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideBottomNavigation();
        }
    }

    @Override
    public void onPause() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showBottomNavigation();
        }
        super.onPause();
    }
}