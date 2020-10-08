package com.example.myhomecare.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myhomecare.R;
import com.example.myhomecare.view.adapter.CategoryPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class CategoryFragment extends Fragment {

    View view;
    TabLayout tabLayout;
    ViewPager categoryViewPager;
    CategoryPagerAdapter categoryPagerAdapter;
    ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.category_tab_layout);
        categoryViewPager = view.findViewById(R.id.category_view_pager);
        back = view.findViewById(R.id.iv_category_back);


        clickEvents();
        initTabLayout();
    }

    private void clickEvents() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Women"));
        tabLayout.addTab(tabLayout.newTab().setText("Men"));
        tabLayout.addTab(tabLayout.newTab().setText("Kids"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        categoryPagerAdapter = new CategoryPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        categoryViewPager.setAdapter(categoryPagerAdapter);
        categoryViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                categoryViewPager.setCurrentItem(tab.getPosition());
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