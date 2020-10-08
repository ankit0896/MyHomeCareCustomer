package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.model.FashionModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FashionSale2Adpater extends RecyclerView.Adapter<FashionSale2Adpater.MyFashionSale2ViewHolder> {

    List<FashionModel> fashionModels = new ArrayList<>();
    Context context;
    OnFashionSaleClickListner saleClickListner;

    public FashionSale2Adpater(List<FashionModel> fashionModels, Context context, OnFashionSaleClickListner saleClickListner) {
        this.fashionModels = fashionModels;
        this.context = context;
        this.saleClickListner = saleClickListner;
    }

    @NonNull
    @Override
    public MyFashionSale2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_fashion2_item, parent, false);
        return new MyFashionSale2ViewHolder(view, saleClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyFashionSale2ViewHolder holder, final int position) {
        final ArrayList<Integer> booleans = new ArrayList<>();

        holder.imageView.setImageResource(fashionModels.get(position).getImage());
        holder.name.setText(fashionModels.get(position).getProductName());
        holder.brand.setText(fashionModels.get(position).getBrandName());
        holder.newItem.setVisibility(View.GONE);
//        holder.ratingNumber.setText(fashionModel.getRatingNumber());
        holder.price.setText(fashionModels.get(position).getPrice() + "$");
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (booleans.contains(fashionModels.get(position).getId())) {
                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    booleans.remove(new Integer(fashionModels.get(position).getId()));
                } else {
                    booleans.add(fashionModels.get(position).getId());
                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
            }
        });

        if (fashionModels.get(position).getOffPrice() == 0) {
            holder.peroff.setVisibility(View.GONE);
            holder.newItem.setVisibility(View.VISIBLE);
            holder.price.setText(fashionModels.get(position).getPrice() + " Rs/-");
            holder.price.setPaintFlags(0);
            holder.offerPrice.setVisibility(View.GONE);
        } else {
            float price = 0;
            float offPrice = fashionModels.get(position).getOffPrice();
            price = (fashionModels.get(position).getPrice() - (fashionModels.get(position).getPrice()*offPrice/100));
            holder.peroff.setVisibility(View.VISIBLE);
            holder.peroff.setText(""+fashionModels.get(position).getOffPrice()+"%");
            holder.price.setText(fashionModels.get(position).getPrice() + " Rs/-");
            holder.price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            holder.offerPrice.setText(new DecimalFormat("##.##").format(price)+" Rs/-");
        }
    }

    @Override
    public int getItemCount() {
        return fashionModels.size();
    }

    public class MyFashionSale2ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, favorite;
        TextView name, brand, price, offerPrice, ratingNumber,peroff,newItem;
        RatingBar fashion2RaingBar;

        public MyFashionSale2ViewHolder(@NonNull View itemView, final OnFashionSaleClickListner saleClickListner) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_fashion2_sale_items_image);
            name = itemView.findViewById(R.id.tv_fashion2_sale_brand_category);
            brand = itemView.findViewById(R.id.tv_fashion2_sale_brand_name);
            price = itemView.findViewById(R.id.tv_fashion2_sale_list_price);
            offerPrice = itemView.findViewById(R.id.tv_fashion2_sale_list_offer_price);
            ratingNumber = itemView.findViewById(R.id.tv_fashion2_sale_rating_number);
            fashion2RaingBar = itemView.findViewById(R.id.rb_fashion2_sale_rating_bar);
            favorite = itemView.findViewById(R.id.iv_favorites_fashion2);
            peroff = itemView.findViewById(R.id.tv_fashion_offer_percentage);
            newItem = itemView.findViewById(R.id.tv_fashion_new_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saleClickListner.onFashionItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnFashionSaleClickListner {
        void onFashionItemClick(int position);
    }
}
