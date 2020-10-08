package com.example.myhomecare.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhomecare.R;
import com.example.myhomecare.view.adapter.OrderDetailsAdapter;
import com.example.myhomecare.model.OrderDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsFragment extends Fragment {

    View view;
    ImageView back;
    TextView orderNumber,orderDate,trackingNumber,quantiyItems;
    TextView address,payment,delivery,discount,amount,reorder,feedback;
    RecyclerView rv_list;
    List<OrderDetailsModel> orderDetailsModels = new ArrayList<>();
    OrderDetailsAdapter orderDetailsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_details, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        back = view.findViewById(R.id.iv_ods_back);
        orderNumber = view.findViewById(R.id.tv_ods_orderNumber);
        orderDate = view.findViewById(R.id.tv_ods_orderDate);
        trackingNumber = view.findViewById(R.id.tv_ods_trackingNumber);
        quantiyItems = view.findViewById(R.id.tv_ods_quantity_items);
        rv_list = view.findViewById(R.id.rv_od_listView);
        address = view.findViewById(R.id.tv_ods_address);
        payment = view.findViewById(R.id.tv_ods_payment_method);
        delivery = view.findViewById(R.id.tv_ods_delivery_method);
        discount = view.findViewById(R.id.tv_ods_discount);
        amount = view.findViewById(R.id.tv_ods_totalAmount);
        reorder = view.findViewById(R.id.tv_ods_reorder_btn);
        feedback = view.findViewById(R.id.tv_ods_leavefeedback_btn);

        loadOrderDetails();
        clickEvents();

    }

    private void clickEvents() {
        reorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    private void loadOrderDetails() {
        if(!orderDetailsModels.isEmpty()){orderDetailsModels.clear();}
        orderDetailsModels.add(new OrderDetailsModel(1,(String.valueOf(R.drawable.face)),"Pullover","Mango1","Grey","L",1,56.35F));
        orderDetailsModels.add(new OrderDetailsModel(2,(String.valueOf(R.drawable.face)),"Pullover","Mango2","Grey","L",1,55.35F));
        orderDetailsModels.add(new OrderDetailsModel(3,(String.valueOf(R.drawable.face)),"Pullover","Mango3","Grey","L",1,53.35F));

        setAdapter(orderDetailsModels);
    }

    private void setAdapter(List<OrderDetailsModel> orderDetailsModels) {
        orderDetailsAdapter = new OrderDetailsAdapter(orderDetailsModels, getActivity());
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_list.setAdapter(orderDetailsAdapter);
    }
}