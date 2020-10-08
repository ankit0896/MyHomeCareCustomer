package com.example.myhomecare.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myhomecare.view.fragment.categoryFragment.KidsCategoryFragment;
import com.example.myhomecare.view.fragment.categoryFragment.MenCategoryFragment;
import com.example.myhomecare.view.fragment.categoryFragment.WomenCategoryFragment;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public CategoryPagerAdapter(@NonNull FragmentManager fm, int mNoOfTabs) {
        super(fm);
        this.mNoOfTabs = mNoOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                WomenCategoryFragment womenCategoryFragment = new WomenCategoryFragment();
                return womenCategoryFragment;
            case 1:
                MenCategoryFragment menCategoryFragment = new MenCategoryFragment();
                return menCategoryFragment;
            case 2:
                KidsCategoryFragment kidsCategoryFragment = new KidsCategoryFragment();
                return kidsCategoryFragment;
            default:
                return new WomenCategoryFragment();
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
