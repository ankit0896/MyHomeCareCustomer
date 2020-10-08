package com.example.myhomecare.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.view.adapter.FashionSale2Adpater;
import com.example.myhomecare.model.FashionModel;

import java.util.ArrayList;
import java.util.List;

public class MainFashion2Fragment extends Fragment implements FashionSale2Adpater.OnFashionSaleClickListner {


    View view;
    RecyclerView rv_sale_list,rv_newItem_list;
    List<FashionModel> models = new ArrayList<>();
    List<FashionModel> newmodels = new ArrayList<>();
    TextView viewall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_fashion2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv_sale_list = view.findViewById(R.id.rv_fashion2_sale_list);
        viewall = view.findViewById(R.id.tv_fashion2_sale_viewall);
        rv_newItem_list = view.findViewById(R.id.rv_fashion2_newItem_list);


        clickEvents();
        loadNewData();
        loadData();
    }

    private void loadNewData() {
        if(!newmodels.isEmpty()){
            newmodels.clear();
        }
        newmodels.add(new FashionModel(601, R.drawable.demo, 2, true, "Dorothy Perkins", "Evening Dress", 125, 0));
        newmodels.add(new FashionModel(602, R.drawable.demo, 5, true, "Stilly", "Sport Dress", 135, 0));
        newmodels.add(new FashionModel(603, R.drawable.demo, 2, true, "Stilly", "Sport Dress", 185, 0));
        newmodels.add(new FashionModel(604, R.drawable.demo, 2, true, "Stilly", "Sport Dress", 146, 0));
        newmodels.add(new FashionModel(605, R.drawable.demo, 1, true, "Stilly", "Sport Dress", 455, 0));


        setNewAdapter(newmodels);
    }

    private void setNewAdapter(List<FashionModel> models) {
        FashionSale2Adpater adapter = new FashionSale2Adpater(models, getActivity(), MainFashion2Fragment.this);
        rv_newItem_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_newItem_list.setAdapter(adapter);
    }

    private void clickEvents() {
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new MainFashion3Fragment());
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
        if (fragment == null) {
            getActivity().onBackPressed();
        }
        return false;

    }

    private void loadData() {
        if(!models.isEmpty()){
            models.clear();
        }
        models.add(new FashionModel(201, R.drawable.demo, 2, true, "Dorothy Perkins", "Evening Dress", 125, 15.5F));
        models.add(new FashionModel(202, R.drawable.demo, 5, true, "Stilly", "Sport Dress", 135, 12.5F));
        models.add(new FashionModel(203, R.drawable.demo, 2, true, "Stilly", "Sport Dress", 185, 10.0F));
        models.add(new FashionModel(204, R.drawable.demo, 2, true, "Stilly", "Sport Dress", 146, 25.0F));
        models.add(new FashionModel(205, R.drawable.demo, 1, true, "Stilly", "Sport Dress", 455, 50.0F));


        setAdapter(models);
    }

    private void setAdapter(List<FashionModel> models) {
        FashionSale2Adpater adapter = new FashionSale2Adpater(models, getActivity(), MainFashion2Fragment.this);
        rv_sale_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_sale_list.setAdapter(adapter);
    }

    @Override
    public void onFashionItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("product_id",models.get(position).getId());
        bundle.putString("productName",models.get(position).getProductName());
        bundle.putString("productBrand",models.get(position).getBrandName());
        bundle.putInt("productPrice",models.get(position).getPrice());
        bundle.putFloat("ratingNumber",models.get(position).getRatingNumber());
        bundle.putInt("imageId",models.get(position).getImage());
        bundle.putInt("productId",models.get(position).getId());
        AddToCartFragment addToCartFragment = new AddToCartFragment();
        addToCartFragment.setArguments(bundle);
        loadFragment(addToCartFragment);
    }
}