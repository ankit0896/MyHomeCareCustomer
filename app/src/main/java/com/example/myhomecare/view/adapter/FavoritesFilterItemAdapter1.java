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

public class FavoritesFilterItemAdapter1 extends RecyclerView.Adapter<FavoritesFilterItemAdapter1.FavoritesFilterItem1ViewHolder> {
    List<String> itemList = new ArrayList<>();
    Context context;
    OnFilterItemClicks onFilterItemClicks;

    public FavoritesFilterItemAdapter1(List<String> itemList, Context context, OnFilterItemClicks onFilterItemClicks) {
        this.itemList = itemList;
        this.context = context;
        this.onFilterItemClicks = onFilterItemClicks;
    }

    @NonNull
    @Override
    public FavoritesFilterItem1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_filter_favorites_items, parent, false);
        return new FavoritesFilterItem1ViewHolder(view, onFilterItemClicks);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesFilterItem1ViewHolder holder, int position) {
        holder.filterItem.setText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class FavoritesFilterItem1ViewHolder extends RecyclerView.ViewHolder {
        TextView filterItem;
        public FavoritesFilterItem1ViewHolder(@NonNull View itemView, OnFilterItemClicks onFilterItemClicks) {
            super(itemView);
            filterItem = itemView.findViewById(R.id.tv_favorites_custom_items);
        }
    }

    public interface OnFilterItemClicks{
        void onItemClick(int position);
    }
}
