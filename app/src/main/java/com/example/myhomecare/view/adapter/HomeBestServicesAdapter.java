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

public class HomeBestServicesAdapter extends RecyclerView.Adapter<HomeBestServicesAdapter.MyBestServicesViewHolder> {

    List<String> list = new ArrayList<>();
    Context context;
    OnBestServicesItemClickListner onBestServicesItemClickListner;

    public HomeBestServicesAdapter(List<String> list, Context context, OnBestServicesItemClickListner onBestServicesItemClickListner) {
        this.list = list;
        this.context = context;
        this.onBestServicesItemClickListner = onBestServicesItemClickListner;
    }

    @NonNull
    @Override
    public MyBestServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_filter_favorites_items, parent, false);
        return new MyBestServicesViewHolder(view, onBestServicesItemClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBestServicesViewHolder holder, int position) {
        holder.items.setText(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyBestServicesViewHolder extends RecyclerView.ViewHolder {
        TextView items;
        public MyBestServicesViewHolder(@NonNull View itemView, final OnBestServicesItemClickListner onBestServicesItemClickListner) {
            super(itemView);
            items = itemView.findViewById(R.id.tv_favorites_custom_items);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBestServicesItemClickListner.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnBestServicesItemClickListner{
        void onItemClick(int position);
    }
}
