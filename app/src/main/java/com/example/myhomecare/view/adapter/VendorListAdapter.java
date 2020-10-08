package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.model.VendorModel;

import java.util.ArrayList;
import java.util.List;

public class VendorListAdapter extends RecyclerView.Adapter<VendorListAdapter.MyVendorListViewHolder> {

    List<VendorModel> models = new ArrayList<>();
    Context context;
    OnVendorItemClick onVendorItemClick;

    public VendorListAdapter(List<VendorModel> models, Context context, OnVendorItemClick onVendorItemClick) {
        this.models = models;
        this.context = context;
        this.onVendorItemClick = onVendorItemClick;
    }

    @NonNull
    @Override
    public MyVendorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_vendor_item, parent, false);
        return new MyVendorListViewHolder(view, onVendorItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVendorListViewHolder holder, int position) {
        VendorModel model = models.get(position);
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice() + " Rs/-");
        if (model.getOfferPrice() == 0) {
            holder.offerprice.setVisibility(View.INVISIBLE);
        } else {
            holder.offerprice.setVisibility(View.VISIBLE);
            holder.offerprice.setText(model.getOfferPrice() + " Rs/-");
            holder.price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyVendorListViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, offerprice,viewDetails;

        public MyVendorListViewHolder(@NonNull View itemView, final OnVendorItemClick onVendorItemClick) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_list_vendor_name);
            price = itemView.findViewById(R.id.tv_list_vendor_price);
            offerprice = itemView.findViewById(R.id.tv_list_vendor_offerprice);
            viewDetails = itemView.findViewById(R.id.tv_viewdetails_vendor_details);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onVendorItemClick.onVendorClick(getAdapterPosition());
                }
            });

            viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        onVendorItemClick.onViewVendorDetails(getAdapterPosition());
                }
            });
        }
    }

    public interface OnVendorItemClick {
        void onVendorClick(int position);
        void onViewVendorDetails(int position);
    }
}
