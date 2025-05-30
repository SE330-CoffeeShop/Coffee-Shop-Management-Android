package com.example.coffeeshopmanagementandroid.ui.fragment.orders;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.DiscountModel;
import com.example.coffeeshopmanagementandroid.domain.model.OrderItemModel;
import com.example.coffeeshopmanagementandroid.domain.model.PaymentMethodModel;
import com.example.coffeeshopmanagementandroid.ui.MainActivity;
import com.example.coffeeshopmanagementandroid.ui.adapter.OrderProductAdapter;
import com.example.coffeeshopmanagementandroid.ui.adapter.PaymentMethodAdapter;
import com.example.coffeeshopmanagementandroid.utils.NavigationUtils;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderFragment extends Fragment {
    private OrderProductAdapter orderProductAdapter;
    private RecyclerView orderProductRecyclerView;
    private PaymentMethodAdapter paymentMethodAdapter;
    private RecyclerView paymentMethodRecyclerView;
    private PaymentMethodModel selectedPaymentMethod;
    private NavController navController;
    private View applyDiscountLayout;
    private ImageButton backButton;
    private MaterialButton buyButton;
    private DiscountModel selectedDiscount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirm_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setupOrderProducts();
        setupPaymentMethods();
        getParentFragmentManager().setFragmentResultListener("discountResult", this, (requestKey, result) -> {
            if (result.containsKey("selectedDiscount")) {
                selectedDiscount = result.getParcelable("selectedDiscount");
                // Cập nhật giao diện hoặc logic với selectedDiscount, ví dụ: hiển thị tên mã giảm giá
            } else {
                selectedDiscount = null;
                // Xử lý trường hợp không chọn mã giảm giá
            }
        });
    }

    private void initViews(View view) {
        navController = NavHostFragment.findNavController(this);
        applyDiscountLayout = view.findViewById(R.id.applyDiscountLayout);
        orderProductRecyclerView = view.findViewById(R.id.orderProductRecyclerView);
        paymentMethodRecyclerView = view.findViewById(R.id.paymentMethodRecyclerView);
        backButton = view.findViewById(R.id.backButtonToCart);
        buyButton = view.findViewById(R.id.buyButton);

        // Set Listener
        backButton.setOnClickListener(v -> handleBackPressed());
        buyButton.setOnClickListener(v -> handleBuyNow());

        // Xử lý bấm vào applyDiscountLayout
        applyDiscountLayout.setOnClickListener(v -> navigateToDiscountFragment());
    }

    private void navigateToDiscountFragment() {
        NavigationUtils.safeNavigate(navController,
                R.id.confirmOrderFragment,
                R.id.action_confirmOrderFragment_to_discountFragment,
                "DiscountFragment",
                "ConfirmOrderFragment",
                null);
    }

    private void setupOrderProducts() {
        List<OrderItemModel> products = new ArrayList<>();
        products.add(new OrderItemModel("https://example.com/cappuccino.jpg", "Classic Cappuccino", "Size M, 100% đá, 50% đường", 2, 45.13));
        products.add(new OrderItemModel("https://example.com/caramel_macchiato.jpg", "Caramel Macchiato", "Size L, 70% đá, 100% đường", 1, 50.00));
        products.add(new OrderItemModel("https://example.com/vanilla_latte.jpg", "Vanilla Latte", "Size S, 50% đá, 30% đường", 3, 48.75));
        products.add(new OrderItemModel("https://example.com/americano.jpg", "Americano", "Size M, không đá, không đường", 2, 35.50));

        orderProductRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        orderProductAdapter = new OrderProductAdapter(products);
        orderProductRecyclerView.setAdapter(orderProductAdapter);
    }

    private void setupPaymentMethods() {
        List<PaymentMethodModel> paymentMethods = new ArrayList<>();
        paymentMethods.add(new PaymentMethodModel("0001", "CASH", "Tiền mặt", true, "user1"));
        paymentMethods.add(new PaymentMethodModel("0002", "BANK_CARD", "Thẻ ngân hàng", false, "user1"));
        paymentMethods.add(new PaymentMethodModel("0003", "MOMO", "MoMo", false, "user1"));
        paymentMethods.add(new PaymentMethodModel("0004", "ZALOPAY", "ZaloPay", false, "user1"));

        // Tìm vị trí phương thức mặc định
        int defaultPosition = -1;
        for (int i = 0; i < paymentMethods.size(); i++) {
            if (paymentMethods.get(i).isPaymentMethodIsDefault()) {
                defaultPosition = i;
                break;
            }
        }

        paymentMethodAdapter = new PaymentMethodAdapter(paymentMethods, defaultPosition, selectedPayment -> {
            this.selectedPaymentMethod = selectedPayment;
        });
        paymentMethodRecyclerView.setAdapter(paymentMethodAdapter);
        paymentMethodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void handleBackPressed() {
        if (getView() != null) {
            Navigation.findNavController(getView()).popBackStack();
        }
    }

    private void handleBuyNow() {
        PaymentMethodModel selectedFromAdapter = paymentMethodAdapter.getSelectedPaymentMethod();
        if (selectedPaymentMethod != null && selectedPaymentMethod.equals(selectedFromAdapter)) {
            Toast.makeText(getContext(), "Phương thức: " + selectedPaymentMethod.getPaymentMethodType(), Toast.LENGTH_SHORT).show();
            // Gửi selectedPaymentMethod lên server hoặc xử lý tiếp
        } else {
            Toast.makeText(getContext(), "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
        }
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
