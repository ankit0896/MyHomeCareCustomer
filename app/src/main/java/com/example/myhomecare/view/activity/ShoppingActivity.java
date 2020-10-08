package com.example.myhomecare.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myhomecare.R;
import com.example.myhomecare.view.fragment.CategoryFragment;
import com.example.myhomecare.view.fragment.FavoritesFragment_1;
import com.example.myhomecare.view.fragment.MainFashionFragment;
import com.example.myhomecare.view.fragment.MyBagFragment;
import com.example.myhomecare.view.fragment.MyProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShoppingActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        loadFragment(new MainFashionFragment());
        initViews();

    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.shoppingBottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = new MainFashionFragment();

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new MainFashionFragment();
                break;

            case R.id.navigation_shop:
               fragment = new CategoryFragment();
                break;

            case R.id.navigation_bag:
                fragment = new MyBagFragment();
                break;

            case R.id.navigation_favorite:
                fragment = new FavoritesFragment_1();
                break;

            case R.id.navigation_profile:
                fragment = new MyProfileFragment();
                break;
        }

        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;

    }
}