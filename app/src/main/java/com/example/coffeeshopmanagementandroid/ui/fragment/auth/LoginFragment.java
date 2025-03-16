package com.example.coffeeshopmanagementandroid.ui.fragment.auth;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.ui.activity.AuthActivity;
import com.example.coffeeshopmanagementandroid.ui.component.SocialButton;
import com.example.coffeeshopmanagementandroid.ui.component.AuthInput;
import com.example.coffeeshopmanagementandroid.ui.component.AuthButton;
import com.example.coffeeshopmanagementandroid.ui.viewmodel.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private AuthInput emailInput;
    private AuthInput passwordInput;
    private AuthButton loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Ánh xạ các thành phần UI
        emailInput = view.findViewById(R.id.email_input);
        passwordInput = view.findViewById(R.id.password_input);
        loginButton = view.findViewById(R.id.sign_in_button);
        SocialButton facebookButton = view.findViewById(R.id.facebook_button);
        SocialButton googleButton = view.findViewById(R.id.google_button);

        // Thiết lập giao diện
        emailInput.setLabel("Email");
        emailInput.setHint("Type your Email");

        passwordInput.setLabel("Password");
        passwordInput.setHint("Enter your password");

        facebookButton.setButtonIcon(R.drawable.facebook_icon);
        facebookButton.setButtonText("Facebook");

        googleButton.setButtonIcon(R.drawable.google_icon);
        googleButton.setButtonText("Google");

        // Xử lý sự kiện bấm đăng nhập
        loginButton.setOnClickListener(v -> handleLogin());

        // Xử lý sự kiện chuyển sang màn hình đăng ký
        view.findViewById(R.id.go_to_sign_up_button).setOnClickListener(v -> navigateToSignUp());

        // Lắng nghe kết quả đăng nhập từ ViewModel
        observeLoginResult();

        return view;
    }

    private void handleLogin() {
        String email = emailInput.getText();
        String password = passwordInput.getText();

        Log.v("Button Click", "Login");

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Email and password must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        loginButton.setEnabled(false); // Vô hiệu hóa nút khi đang xử lý
        loginViewModel.login(email, password, false);
    }

    private void observeLoginResult() {
        loginViewModel.getAuthLiveData().observe(getViewLifecycleOwner(), authModel -> {
            loginButton.setEnabled(true); // Bật lại nút khi có kết quả

            if (authModel != null) {
                Toast.makeText(getContext(), "Login Success!", Toast.LENGTH_SHORT).show();
                // Điều hướng sang màn hình chính hoặc xử lý tiếp theo
            } else {
                Toast.makeText(getContext(), "Login Failed. Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToSignUp() {
        if (getActivity() instanceof AuthActivity) {
            ((AuthActivity) getActivity()).switchFragment(new RegisterFragment());
        }
    }
}
