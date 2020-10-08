package com.example.myhomecare.view.fragment.categoryFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myhomecare.R;
import com.example.myhomecare.view.fragment.WomensFragment_1;

public class WomenCategoryFragment extends Fragment {

    View view;
    CardView clothes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_women_category, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        clothes = view.findViewById(R.id.card_womens_fragment_clothes);

        clickEvents();
    }

    private void clickEvents() {
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new WomensFragment_1());
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