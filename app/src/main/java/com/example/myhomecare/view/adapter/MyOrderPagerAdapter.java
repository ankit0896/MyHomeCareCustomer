package com.example.myhomecare.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myhomecare.view.fragment.orderFragment.CancelledFragment;
import com.example.myhomecare.view.fragment.orderFragment.DeliveredFragment;
import com.example.myhomecare.view.fragment.orderFragment.ProcessingFragment;

public class MyOrderPagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public MyOrderPagerAdapter(@NonNull FragmentManager fm, int mNoOfTabs) {
        super(fm);
        this.mNoOfTabs = mNoOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DeliveredFragment deliveredFragment = new DeliveredFragment();
                return deliveredFragment;
            case 1:
                ProcessingFragment processingFragment = new ProcessingFragment();
                return processingFragment;
            case 2:
                CancelledFragment cancelledFragment = new CancelledFragment();
                return cancelledFragment;
            default:
                return new DeliveredFragment();
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
