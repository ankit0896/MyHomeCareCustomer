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

public class HomeTopCategoryAdapter extends RecyclerView.Adapter<HomeTopCategoryAdapter.MyCategoryViewHolder> {

    List<String> list = new ArrayList<>();
    Context context;
    OnCategoryClickListner listner;

    public HomeTopCategoryAdapter(List<String> list, Context context, OnCategoryClickListner listner) {
        this.list = list;
        this.context = context;
        this.listner = listner;
    }

    @NonNull
    @Override
    public MyCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_filter_favorites_items, parent, false);
        return new MyCategoryViewHolder(view, listner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCategoryViewHolder holder, int position) {
        holder.items.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView items;
        public MyCategoryViewHolder(@NonNull View itemView, final OnCategoryClickListner listner) {
            super(itemView);
            items = itemView.findViewById(R.id.tv_favorites_custom_items);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.onBestItemClick(getAdapterPosition());
                }
            });
        }
    }


    public interface OnCategoryClickListner{
        void onBestItemClick(int postion);
    }
}
