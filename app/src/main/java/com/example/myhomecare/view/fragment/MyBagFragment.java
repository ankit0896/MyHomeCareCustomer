package com.example.myhomecare.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.view.activity.SuccessActivity;
import com.example.myhomecare.view.adapter.CartAdapter;
import com.example.myhomecare.database.CartSqliteHelper;
import com.example.myhomecare.model.CartModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;


public class MyBagFragment extends Fragment implements CartAdapter.OnCartBagListner {

    View view;
    RecyclerView bag_items;
    TextView totalAmount;
    List<CartModel> cartModelList = new ArrayList<>();
    CartSqliteHelper cartSqliteHelper;
    CartAdapter cartAdapter;
    List<CartModel> countTotal = new ArrayList<>();
    TextView checkout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_bag, container, false);
        cartSqliteHelper = new CartSqliteHelper(getActivity());
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        bag_items = view.findViewById(R.id.rv_my_cart_bag);
        totalAmount = view.findViewById(R.id.tv_cart_bag_total_amount);
        checkout = view.findViewById(R.id.tv_checkout_btn);

        loadCartItems();
        clickEvents();
    }

    private void clickEvents() {
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO send and delete function for Sqlite Data
                startActivity(new Intent(getActivity(), SuccessActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    private void loadCartItems() {
        if (!cartModelList.isEmpty()) {
            cartModelList.clear();
        }
        cartModelList = cartSqliteHelper.getAllProducts();
        int price = 0;
        countTotal = cartSqliteHelper.getAllProducts();
        for (CartModel c : countTotal) {
            price = price + (int) floor(c.getQuantity() * c.getOfferPrice());
        }
        totalAmount.setText(price+" Rs/-");
        setCartListAdapter(cartModelList);
    }

    private void setCartListAdapter(List<CartModel> cartModelList) {
        cartAdapter = new CartAdapter(cartModelList, getActivity(), MyBagFragment.this);
        bag_items.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        bag_items.setAdapter(cartAdapter);
    }

    @Override
    public void onAddClickListner(int position) {
        int price = 0;
        countTotal = cartSqliteHelper.getAllProducts();
        for (CartModel c : countTotal) {
            price = price + (int) floor(c.getQuantity() * c.getOfferPrice());
        }
        totalAmount.setText(price+" Rs/-");
    }

    @Override
    public void onSubtractClickListner(int position) {
        int price = 0;
        countTotal = cartSqliteHelper.getAllProducts();
        for (CartModel c : countTotal) {
            price = price + (int) floor(c.getQuantity() * c.getOfferPrice());
        }
        totalAmount.setText(price+" Rs/-");
    }

    @Override
    public void onOptionClickListner(int position) {

    }

    @Override
    public void onDeleteProduct(int position) {
        cartSqliteHelper.deleteProduct(cartModelList.get(position).getProductId());
        loadCartItems();
    }
}