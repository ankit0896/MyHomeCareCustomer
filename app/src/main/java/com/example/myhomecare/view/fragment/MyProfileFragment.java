package com.example.myhomecare.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomecare.R;
import com.example.myhomecare.view.activity.RatingReviewsActivity;
import com.example.myhomecare.view.activity.SettingsActivity;

public class MyProfileFragment extends Fragment {

    View view;
    ConstraintLayout settingLayout,myorder,myreviews;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_my_profile, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        settingLayout = view.findViewById(R.id.cl_profile_settings);
        myorder = view.findViewById(R.id.cl_myprofile_myorder);
        myreviews = view.findViewById(R.id.cl_myreviews);

        clickEvents();

    }

    private void clickEvents() {
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });

        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new MyOrderFragment());
            }
        });
        myreviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RatingReviewsActivity.class));
            }
        });
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack(null)
                    .commit();
            return true;
        }
        return false;

    }
}