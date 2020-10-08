package com.example.myhomecare.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myhomecare.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SettingsActivity extends AppCompatActivity {

    TextView passwordChange;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        initViews();
    }

    private void initViews() {
        passwordChange = findViewById(R.id.tv_setting_password_change);
        back = findViewById(R.id.iv_settings_back);


        clickEvents();
    }

    private void clickEvents() {
        passwordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openBottomSheet();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void openBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SettingsActivity.this, R.style.BottomSheetDialogTheme);
        View view = LayoutInflater.from(SettingsActivity.this).inflate(R.layout.change_password_bottom_sheet, (LinearLayout)findViewById(R.id.bottom_sheet_container));
        bottomSheetDialog.setContentView(view);

        bottomSheetDialog.show();
    }

    public void switchChange(View view) {
        int myColor = getResources().getColor(R.color.colorGreen);
        int defaultColor = getResources().getColor(R.color.colorGrey);
        boolean checked = ((Switch) view).isChecked();
        if (checked) {
            ((Switch) view).getThumbDrawable().setColorFilter(myColor,
                    PorterDuff.Mode.MULTIPLY);
            ((Switch) view).getTrackDrawable().setColorFilter(myColor,
                    PorterDuff.Mode.MULTIPLY);
        } else {
            ((Switch) view).getThumbDrawable().setColorFilter(defaultColor,
                    PorterDuff.Mode.MULTIPLY);
            ((Switch) view).getTrackDrawable().setColorFilter(defaultColor,
                    PorterDuff.Mode.MULTIPLY);
        }
    }
}