package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.model.WomensTopModel;

import java.util.ArrayList;
import java.util.List;

public class WomensTops1Adapter extends RecyclerView.Adapter<WomensTops1Adapter.MyWomensTops1ViewHolder> {
    List<WomensTopModel> modelList = new ArrayList<>();
    Context context;
    OnWomenTop1ItemClickListner listner;

    public WomensTops1Adapter(List<WomensTopModel> modelList, Context context, OnWomenTop1ItemClickListner listner) {
        this.modelList = modelList;
        this.context = context;
        this.listner = listner;
    }

    @NonNull
    @Override
    public MyWomensTops1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_womenstops_vertical_layout, parent, false);
        return new MyWomensTops1ViewHolder(view, listner);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyWomensTops1ViewHolder holder, final int position) {
        final ArrayList<Integer> booleans = new ArrayList<>();
        holder.iv.setImageResource(modelList.get(position).getImage());
        holder.brandName.setText(modelList.get(position).getBrandName());
        holder.productName.setText(modelList.get(position).getProductName());
        holder.ratingBar.setRating(modelList.get(position).getRatingNumber());
        holder.ratingNumber.setText("(" + modelList.get(position).getRatingNumber() + ")");
        holder.price.setText(modelList.get(position).getPrice() + " Rs/-");
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (booleans.contains(modelList.get(position).getId())) {
                    holder.favorite.setImageResource(R.drawable.ic_sharp_favorite_border_24);
                    booleans.remove(new Integer(modelList.get(position).getId()));
                } else {
                    booleans.add(modelList.get(position).getId());
                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyWomensTops1ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, brandName, ratingNumber, price;
        ImageView iv,favorite;
        RatingBar ratingBar;

        public MyWomensTops1ViewHolder(@NonNull View itemView, final OnWomenTop1ItemClickListner listner) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_wt1_item);
            productName = itemView.findViewById(R.id.tv_wt1_productName);
            brandName = itemView.findViewById(R.id.tv_wt1_brandName);
            ratingBar = itemView.findViewById(R.id.rb_wt1_rating);
            ratingNumber = itemView.findViewById(R.id.tv_wt1_ratingNumber);
            price = itemView.findViewById(R.id.tv_wt1_price);
            favorite = itemView.findViewById(R.id.iv_wt1_favorite_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.onWomensItemClickListner(getAdapterPosition());
                }
            });


        }
    }

    public interface OnWomenTop1ItemClickListner {
        void onFavoriteClick(int position);

        void onWomensItemClickListner(int position);
    }
}
