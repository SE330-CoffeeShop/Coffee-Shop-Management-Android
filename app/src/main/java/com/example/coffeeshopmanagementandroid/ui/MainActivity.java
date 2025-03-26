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
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.ui.activity.AuthActivity;
import com.example.coffeeshopmanagementandroid.ui.adapter.TabAdapter;
import com.example.coffeeshopmanagementandroid.ui.fragment.auth.LoginFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class MainActivity extends AppCompatActivity {
    private BottomAppBar bottomAppBar;
    private NavOptions navOptions;
    private NavController navController;

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
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_navigation);
        if (navHostFragment != null) {
            NavOptions.Builder builder = new NavOptions.Builder();
            builder.setLaunchSingleTop(true);
            navOptions = builder.build();

            navController = navHostFragment.getNavController();
            navController.navigate(R.id.home_navigation);

        }
        bottomAppBar = findViewById(R.id.bottomAppBar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//        if (savedInstanceState == null) {
//            loadFragment(new LoginFragment());
//        }
    }

    // Để lại chưa làm

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