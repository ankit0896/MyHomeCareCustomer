package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;

import java.util.ArrayList;
import java.util.List;

public class MasterHomeGridAdapter extends RecyclerView.Adapter<MasterHomeGridAdapter.GridViewHolder> {
    List<String> listOfImage = new ArrayList<>();
    Context context;
    OnMasterGridListner onMasterGridListner;

    public MasterHomeGridAdapter(List<String> listOfImage, Context context, OnMasterGridListner onMasterGridListner) {
        this.listOfImage = listOfImage;
        this.context = context;
        this.onMasterGridListner = onMasterGridListner;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_masterhome_griditem,parent,false);
        return new GridViewHolder(view,onMasterGridListner);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        holder.imageView.setImageResource(new Integer(listOfImage.get(position)));
    }

    @Override
    public int getItemCount() {
        return listOfImage.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public GridViewHolder(@NonNull View itemView, OnMasterGridListner onMasterGridListner) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_grid_image);
        }
    }

    public interface OnMasterGridListner{
        void onGridClickListner(int postion);
    }
}
