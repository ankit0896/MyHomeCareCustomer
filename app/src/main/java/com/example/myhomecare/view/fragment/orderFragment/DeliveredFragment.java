package com.example.myhomecare.view.fragment.orderFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.view.adapter.DeliveredOrderAdapter;
import com.example.myhomecare.view.fragment.OrderDetailsFragment;
import com.example.myhomecare.model.OrderDetailModel;

import java.util.ArrayList;
import java.util.List;

public class DeliveredFragment extends Fragment implements DeliveredOrderAdapter.OrderedDeliveredListner {

    View view;
    RecyclerView list;
    List<OrderDetailModel> modelList = new ArrayList<>();
    DeliveredOrderAdapter deliveredOrderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_delivered, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        list = view.findViewById(R.id.rv_delivered_order_list);

        loadDeliveredData();
    }

    private void loadDeliveredData() {
        if(!modelList.isEmpty()){
            modelList.clear();
        }
        modelList.add(new OrderDetailModel(1,"1561246254","02-11-2020","25161616251",5,56.25F));
        modelList.add(new OrderDetailModel(2,"1561246254","02-11-2020","25161616251",5,56.25F));
        modelList.add(new OrderDetailModel(3,"1561246254","02-11-2020","25161616251",5,56.25F));
        modelList.add(new OrderDetailModel(4,"1561246254","02-11-2020","25161616251",5,56.25F));
        modelList.add(new OrderDetailModel(5,"1561246254","02-11-2020","25161616251",5,56.25F));
        modelList.add(new OrderDetailModel(6,"1561246254","02-11-2020","25161616251",5,56.25F));
        modelList.add(new OrderDetailModel(7,"1561246254","02-11-2020","25161616251",5,56.25F));
        modelList.add(new OrderDetailModel(8,"1561246254","02-11-2020","25161616251",5,56.25F));

        setAdapter(modelList);

    }

    private void setAdapter(List<OrderDetailModel> modelList) {
        deliveredOrderAdapter = new DeliveredOrderAdapter(modelList, getActivity(), DeliveredFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(linearLayoutManager);
        list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        list.setAdapter(deliveredOrderAdapter);
    }

    @Override
    public void onDetailsClickListner(int position) {
       loadFragment(new OrderDetailsFragment());
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