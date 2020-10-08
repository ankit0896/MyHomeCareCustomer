package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;

import java.util.ArrayList;
import java.util.List;

public class CartBrandAdpter extends RecyclerView.Adapter<CartBrandAdpter.MyCartBrandAdapterViewHolder> {
    List<String> brandlist = new ArrayList<>();
    Context context;
    OnBrandClickListner brandClickListner;

    public CartBrandAdpter(List<String> brandlist, Context context, OnBrandClickListner brandClickListner) {
        this.brandlist = brandlist;
        this.context = context;
        this.brandClickListner = brandClickListner;
    }

    @NonNull
    @Override
    public MyCartBrandAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_brand_layout, parent, false);
        return new MyCartBrandAdapterViewHolder(view, brandClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartBrandAdapterViewHolder holder, int position) {
        holder.brandName.setText(brandlist.get(position));
    }

    @Override
    public int getItemCount() {
        return brandlist.size();
    }

    public class MyCartBrandAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView brandName;

        public MyCartBrandAdapterViewHolder(@NonNull View itemView, final OnBrandClickListner brandClickListner) {
            super(itemView);
            brandName = itemView.findViewById(R.id.tv_list_brand_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    brandClickListner.onCartBrandItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnBrandClickListner {
        void onCartBrandItemClick(int position);
    }
}
