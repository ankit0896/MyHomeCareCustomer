package com.example.myhomecare.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.example.myhomecare.R;
import com.example.myhomecare.view.adapter.MasterHomeGridAdapter;
import com.example.myhomecare.view.adapter.MasterHomePagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MasterHomeActivity extends AppCompatActivity implements MasterHomeGridAdapter.OnMasterGridListner {

    LoopingViewPager viewPager;
    MasterHomePagerAdapter adapter;
    MasterHomeGridAdapter masterHomeGridAdapter;
    ImageView banner;
    RecyclerView dealList;
    ImageView sales, food, services, grocery, cab;
    ArrayList<Integer> masterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        initViews();
    }

    private void initViews() {
        sales = findViewById(R.id.iv_masterhome_sale);
        viewPager = findViewById(R.id.view_pager_master_home);
        banner = findViewById(R.id.iv_grid_master_poster);
        dealList = findViewById(R.id.rv_grid_master_home);






        /*
        Load Data
         */

        loadDealsData();
        setViewPagerAdapter();

        clickEvents();


    }

    private void loadDealsData() {
        List<String> dealItem = new ArrayList<>();
        dealItem.add(String.valueOf(R.drawable.face));
        dealItem.add(String.valueOf(R.drawable.face));
        dealItem.add(String.valueOf(R.drawable.face));
        dealItem.add(String.valueOf(R.drawable.face));
        masterHomeGridAdapter = new MasterHomeGridAdapter(dealItem, MasterHomeActivity.this,MasterHomeActivity.this);
        dealList.setLayoutManager(new GridLayoutManager(this, 2));
        dealList.setAdapter(masterHomeGridAdapter);

    }

    private void setViewPagerAdapter() {
        adapter = new MasterHomePagerAdapter(this, createDummyItems(), true);
        viewPager.setAdapter(adapter);
    }

    private ArrayList<Integer> createDummyItems() {
        masterList.add(R.drawable.face);
        masterList.add(R.drawable.model);
        masterList.add(R.drawable.face);
        masterList.add(R.drawable.model);
        masterList.add(R.drawable.face);
        return masterList;
    }

    private void clickEvents() {
        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MasterHomeActivity.this, HomeActivity.class));
            }
        });
    }

    @Override
    public void onGridClickListner(int postion) {
        Toast.makeText(this, "" + postion, Toast.LENGTH_SHORT).show();
    }
}