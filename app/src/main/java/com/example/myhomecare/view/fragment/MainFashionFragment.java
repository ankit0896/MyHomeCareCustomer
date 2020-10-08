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

public class MainFashionFragment extends Fragment implements FashionSale2Adpater.OnFashionSaleClickListner{
    View view;
    TextView check;
    RecyclerView rv_mainfashionList;
    List<FashionModel> mainFaishionList = new ArrayList<>();
    FashionSale2Adpater fashionSale2Adpater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_fashion, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        check = view.findViewById(R.id.tv_shopping_check_sale);
        rv_mainfashionList = view.findViewById(R.id.rv_main_fashion_list);

        loadMainFashionListItems();
        clickEvents();
    }

    private void loadMainFashionListItems() {
        if(!mainFaishionList.isEmpty()){
            mainFaishionList.clear();
        }
        mainFaishionList.add(new FashionModel(101,R.drawable.face,3.2F,false,"Mango1","lower",150,0));
        mainFaishionList.add(new FashionModel(102,R.drawable.face,3.5F,false,"Mang2o","lower",160,0));
        mainFaishionList.add(new FashionModel(103,R.drawable.face,3.00F,true,"Mango3","lower",170,0));
        mainFaishionList.add(new FashionModel(104,R.drawable.face,4.2F,false,"Mango4","lower",180,0));
        mainFaishionList.add(new FashionModel(105,R.drawable.face,5.6F,true,"Mango5","lower",190,0));
        mainFaishionList.add(new FashionModel(106,R.drawable.face,1.2F,false,"Mango6","lower",250,0));
        setAdapter(mainFaishionList);
    }

    private void setAdapter(List<FashionModel> mainFaishionList) {
        fashionSale2Adpater = new FashionSale2Adpater(mainFaishionList, getActivity(), MainFashionFragment.this);
        rv_mainfashionList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_mainfashionList.setAdapter(fashionSale2Adpater);
    }

    private void clickEvents() {
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new MainFashion2Fragment());
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

    @Override
    public void onFashionItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("product_id",mainFaishionList.get(position).getId());
        bundle.putString("productName",mainFaishionList.get(position).getProductName());
        bundle.putString("productBrand",mainFaishionList.get(position).getBrandName());
        bundle.putInt("productPrice",mainFaishionList.get(position).getPrice());
        bundle.putFloat("ratingNumber",mainFaishionList.get(position).getRatingNumber());
        bundle.putFloat("peroff",mainFaishionList.get(position).getOffPrice());
        bundle.putInt("imageId",mainFaishionList.get(position).getImage());
        bundle.putInt("productId",mainFaishionList.get(position).getId());
        AddToCartFragment addToCartFragment = new AddToCartFragment();
        addToCartFragment.setArguments(bundle);
        loadFragment(addToCartFragment);
    }
}