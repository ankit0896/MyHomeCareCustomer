package com.example.myhomecare.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhomecare.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText name,email,password;
    ImageView nameCheck,emailCheck,back;
    TextView alreadyAccount;
    CardView google,facebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        initViews();
    }

    private void initViews() {
        name = findViewById(R.id.tie_signup_name);
        email = findViewById(R.id.tie_signup_email);
        password = findViewById(R.id.tie_signup_password);
        nameCheck = findViewById(R.id.iv_name_signup_check);
        emailCheck = findViewById(R.id.iv_email_signup_check);
        alreadyAccount = findViewById(R.id.tv_signup_already_have_account);
        google = findViewById(R.id.cv_signup_google);
        facebook = findViewById(R.id.cv_signup_facebook);
        back = findViewById(R.id.iv_signup_back);

        clickEvents();
    }

    private void clickEvents() {
        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}