package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.model.FavoriteModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FavoriteListAdpater extends RecyclerView.Adapter<FavoriteListAdpater.MyFavoritesListViewHolder> {


    List<FavoriteModel> favoriteModels = new ArrayList<>();
    Context context;
    OnfavoriteClickListner onFilterItemClicks;

    public FavoriteListAdpater(List<FavoriteModel> favoriteModels, Context context, OnfavoriteClickListner onFilterItemClicks) {
        this.favoriteModels = favoriteModels;
        this.context = context;
        this.onFilterItemClicks = onFilterItemClicks;
    }

    @NonNull
    @Override
    public MyFavoritesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_favorite_item, parent, false);
        return new MyFavoritesListViewHolder(view, onFilterItemClicks);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavoritesListViewHolder holder, final int position) {
        FavoriteModel favoriteModel = favoriteModels.get(position);

        holder.itemImage.setImageResource(favoriteModel.getImage());
        holder.ratingNumber.setText("("+favoriteModel.getRatingNumber()+")");
        holder.ratingBar.setRating(favoriteModel.getRatingNumber());
        holder.brandName.setText(favoriteModel.getBrandname());
        holder.newitem.setVisibility(View.GONE);
        holder.productName.setText(favoriteModel.getProductName());
        holder.price.setText(favoriteModel.getPrice()+"$");
        if(favoriteModel.getOfferPer()==0){
            holder.offerPrice.setVisibility(View.INVISIBLE);
            holder.price.setText(favoriteModel.getPrice()+"$");
            holder.price.setPaintFlags(0);
        }else{
            float price = 0;
            float offper = favoriteModel.getOfferPer();
            price = (favoriteModel.getPrice()-((favoriteModel.getPrice()*offper/100)));
            holder.offerPrice.setVisibility(View.VISIBLE);
            holder.peroff.setVisibility(View.VISIBLE);
            holder.peroff.setText(favoriteModel.getOfferPer()+"%");
            holder.price.setText(favoriteModel.getPrice()+" Rs/-");
            holder.price.setPaintFlags( Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            holder.offerPrice.setText(new DecimalFormat("##.##").format(price)+" Rs/-");
        }

        if(!favoriteModel.isAvailable()){
            holder.soldOut.setVisibility(View.VISIBLE);
            holder.cartbag.setVisibility(View.GONE);
            holder.ll.setAlpha((float) 0.5);
            holder.peroff.setAlpha((float)0.5);
            holder.constraintLayout.setAlpha((float) 0.5);
            holder.productLayout.setAlpha((float) 0.5);
        }else{
            holder.soldOut.setVisibility(View.GONE);
            holder.cartbag.setVisibility(View.VISIBLE);
            holder.ll.setAlpha(1);
            holder.peroff.setAlpha(1);
            holder.constraintLayout.setAlpha(1);
            holder.productLayout.setAlpha(1);

        }

        holder.cartbag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFilterItemClicks.onbagClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return favoriteModels.size();
    }

    public class MyFavoritesListViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage,cartbag;
        TextView brandName,ratingNumber,productName,color,size,price,offerPrice,newitem,peroff,soldOut;
        RatingBar ratingBar;
        ConstraintLayout constraintLayout,item_custom;
        LinearLayout ll,productLayout;

        public MyFavoritesListViewHolder(@NonNull View itemView, final OnfavoriteClickListner onFilterItemClicks) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.iv_favorites_items_image);
            cartbag = itemView.findViewById(R.id.favorite_cart_bag);
            brandName = itemView.findViewById(R.id.tv_favorites_brand_name);
            ratingNumber = itemView.findViewById(R.id.favorite_rating_number);
            productName = itemView.findViewById(R.id.tv_favorites_brand_category);
            color = itemView.findViewById(R.id.tv_favorites_brand_color);
            size = itemView.findViewById(R.id.tv_favorites_brand_size);
            price = itemView.findViewById(R.id.tv_favorites_list_price);
            offerPrice = itemView.findViewById(R.id.tv_favorites_list_offer_price);
            ratingBar = itemView.findViewById(R.id.favorite_rating_bar);
            newitem  =itemView.findViewById(R.id.tv_favorite_new_item);
            peroff = itemView.findViewById(R.id.tv_favorite_offer_percentage);
            soldOut = itemView.findViewById(R.id.tv_favorite1_sold_out_item);
            constraintLayout = itemView.findViewById(R.id.favoriteConstrLayout);
            ll = itemView.findViewById(R.id.llayout);
            productLayout = itemView.findViewById(R.id.llproductLayout);
            item_custom = itemView.findViewById(R.id.cl_favorite_custom_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onFilterItemClicks.onItemClick(getAdapterPosition());
                }
            });

        }
    }

    public interface OnfavoriteClickListner{
        void onItemClick(int position);
        void onbagClick(int position);
    }
}
