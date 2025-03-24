package com.example.coffeeshopmanagementandroid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.ui.activity.AuthActivity;
import com.example.coffeeshopmanagementandroid.ui.adapter.TabAdapter;
import com.example.coffeeshopmanagementandroid.ui.fragment.auth.LoginFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        // Kiểm tra trạng thái đăng nhập
//        if (!isUserLoggedIn()) {
//            // Chuyển sang AuthActivity
//            Intent intent = new Intent(this, AuthActivity.class);
//            startActivity(intent);
//            finish(); // Đóng MainActivity để không quay lại màn hình này
//            return;
//        }

        // Nếu đã đăng nhập, tiếp tục hiển thị MainActivity
        setContentView(R.layout.activity_main);

        setupTabs();

//        if (savedInstanceState == null) {
//            loadFragment(new LoginFragment());
//        }
    }

    // Để lại chưa làm
    private void setupTabs() {
        ViewPager2 viewPaper = findViewById(R.id.viewPagerCoffee);
        viewPaper.setUserInputEnabled(false);
        TabAdapter tabAdapter = new TabAdapter(this);
        viewPaper.setAdapter(tabAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayoutCoffee);
        new TabLayoutMediator(tabLayout, viewPaper, (tab, position) -> {
            // Áp dụng custom layout cho từng tab
            setCustomTabView(tab, position);
        }).attach();
        // Cập nhật màu sắc cho tab đầu tiên (tab được chọn mặc định)
        tabAppearance(Objects.requireNonNull(tabLayout.getTabAt(0)), true);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Thay đổi icon và text khi tab được chọn
                tabAppearance(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Thay đổi icon và text khi tab khác được chọn
                tabAppearance(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Xử lý khi tab được chọn lại (nếu cần)
                tabAppearance(tab, true);
            }

        });

    }

    private void setCustomTabView(TabLayout.Tab tab, int position) {
        // Tạo view từ layout custom_tab.xml
        View customView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

        // Lấy các thành phần trong custom layout
        ImageView tabIcon = customView.findViewById(R.id.tab_icon);
        TextView tabText = customView.findViewById(R.id.tab_text);

        // Thiết lập icon và text cho từng tab
        switch (position) {
            case 0:
                tabIcon.setImageResource(R.drawable.home_icon);
                tabText.setText("Home");
                break;
            case 1:
                tabIcon.setImageResource(R.drawable.orders_icon);
                tabText.setText("Orders");
                break;
            case 2:
                tabIcon.setImageResource(R.drawable.favorites_icon);
                tabText.setText("Favorites");
                break;
            case 3:
                tabIcon.setImageResource(R.drawable.others_icon);
                tabText.setText("Others");
                break;
        }
        // Áp dụng custom view cho tab
        tab.setCustomView(customView);
    }

    private void tabAppearance(TabLayout.Tab tab, boolean isSelected) {
        View customView = tab.getCustomView();
        if(customView != null) {
            ImageView tabIcon = customView.findViewById(R.id.tab_icon);
            TextView tabText = customView.findViewById(R.id.tab_text);

            if(isSelected) {
                tabIcon.setColorFilter(getResources().getColor(R.color.primary_500));
                tabText.setTextColor(getResources().getColor(R.color.primary_500));
            } else {
                tabIcon.setColorFilter(getResources().getColor(R.color.black));
                tabText.setTextColor(getResources().getColor(R.color.black));
            }
        }
    }

    private boolean isUserLoggedIn() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        return prefs.getBoolean("is_logged_in", false); // Mặc định là false nếu chưa có dữ liệu
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_main_container, fragment)
                .commit();
    }
}