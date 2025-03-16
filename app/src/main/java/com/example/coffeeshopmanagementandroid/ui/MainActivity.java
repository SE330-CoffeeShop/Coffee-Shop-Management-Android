package com.example.coffeeshopmanagementandroid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.ui.activity.AuthActivity;
import com.example.coffeeshopmanagementandroid.ui.fragment.auth.LoginFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kiểm tra trạng thái đăng nhập
        if (!isUserLoggedIn()) {
            // Chuyển sang AuthActivity
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
            finish(); // Đóng MainActivity để không quay lại màn hình này
            return;
        }

        // Nếu đã đăng nhập, tiếp tục hiển thị MainActivity
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(new LoginFragment());
        }
    }

    private boolean isUserLoggedIn() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        return prefs.getBoolean("is_logged_in", false); // Mặc định là false nếu chưa có dữ liệu
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}