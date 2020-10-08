package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.model.FilterColorModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FilterColorAdapter extends RecyclerView.Adapter<FilterColorAdapter.FilterColorViewHolder> {

    List<FilterColorModel> colorList = new ArrayList<>();
    Context context;
    OnFilterColorClickListner filterColorClickListner;
     List<Integer> colorSelectedList = new ArrayList<>();

    public FilterColorAdapter(List<FilterColorModel> colorList, Context context, OnFilterColorClickListner filterColorClickListner) {
        this.colorList = colorList;
        this.context = context;
        this.filterColorClickListner = filterColorClickListner;
    }

    @NonNull
    @Override
    public FilterColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_filter_color, parent, false);
        return new FilterColorViewHolder(view, filterColorClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterColorViewHolder holder, final int position) {

        holder.imageView.setImageResource(Integer.parseInt(colorList.get(position).getColor()));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (colorSelectedList.contains(colorList.get(position).getId())) {
                    colorSelectedList.remove(new Integer(colorList.get(position).getId()));
                    holder.imageView.setBackground(null);
                    filterColorClickListner.onFilterColorItemClick(position,colorSelectedList);
                } else {
                    colorSelectedList.add(colorList.get(position).getId());
                    holder.imageView.setBackground(context.getResources().getDrawable(R.drawable.color_back));
                    filterColorClickListner.onFilterColorItemClick(position,colorSelectedList);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class FilterColorViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;

        public FilterColorViewHolder(@NonNull View itemView, final OnFilterColorClickListner filterColorClickListner) {
            super(itemView);
            imageView = itemView.findViewById(R.id.civ_color_filter_item);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    filterColorClickListner.onFilterColorItemClick(getAdapterPosition(),colorSelectedList);
                }
            });
        }
    }

    public interface OnFilterColorClickListner {
        void onFilterColorItemClick(int postion,List<Integer> colorSelectedList);
    }
}
