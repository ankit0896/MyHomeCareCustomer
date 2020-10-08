package com.example.myhomecare.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myhomecare.R;
import com.example.myhomecare.view.adapter.MyOrderPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MyOrderFragment extends Fragment {

    View view;
    TabLayout tabLayout;
    ViewPager myorderViewPager;
    ImageView back;
    MyOrderPagerAdapter myOrderPagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_order, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.myorder_tab_layout);
        myorderViewPager = view.findViewById(R.id.myorder_view_pager);
        back = view.findViewById(R.id.iv_myorder_back);


        clickEvents();
        initTabLayout();
    }

    private void initTabLayout() {

    }

    private void clickEvents() {
        tabLayout.addTab(tabLayout.newTab().setText("Delivered"));
        tabLayout.addTab(tabLayout.newTab().setText("Processing"));
        tabLayout.addTab(tabLayout.newTab().setText("Cancelled"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        myOrderPagerAdapter = new MyOrderPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        myorderViewPager.setAdapter(myOrderPagerAdapter);
        myorderViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myorderViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}