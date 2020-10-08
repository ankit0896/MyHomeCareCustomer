package com.example.myhomecare.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myhomecare.R;
import com.google.android.material.textfield.TextInputEditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    LinearLayout forgotLinearLayout;
    TextInputEditText email;
    ImageView clear,back;
    TextView wrongEmail;
    CardView send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        initViews();
    }

    private void initViews() {
        forgotLinearLayout = findViewById(R.id.ll_forgot_email);
        email = findViewById(R.id.tie_forgot_email);
        clear = findViewById(R.id.iv_forgot_clear);
        wrongEmail = findViewById(R.id.tv_not_valid_email);
        send =findViewById(R.id.card_forgot_email_send_btn);
        back= findViewById(R.id.iv_forgot_back);
        
        clickEvents();

    }

    private void clickEvents() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().contains("@")){
                    forgotLinearLayout.setBackgroundResource(0);
                    clear.setVisibility(View.INVISIBLE);
                    wrongEmail.setVisibility(View.INVISIBLE);

                    // TODO
                }else{
                    forgotLinearLayout.setBackground(ContextCompat.getDrawable(ForgotPasswordActivity.this, R.drawable.forgot_error_back) );
                    clear.setVisibility(View.VISIBLE);
                    wrongEmail.setVisibility(View.VISIBLE);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setText(null);
            }
        });
    }
}