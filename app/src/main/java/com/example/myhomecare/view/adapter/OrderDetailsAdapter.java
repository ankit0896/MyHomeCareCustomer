package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.model.OrderDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder> {

    List<OrderDetailsModel> orderDetailsModels = new ArrayList<>();
    Context context;

    public OrderDetailsAdapter(List<OrderDetailsModel> orderDetailsModels, Context context) {
        this.orderDetailsModels = orderDetailsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_orderdetails_item, parent, false);
        return new OrderDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {
        OrderDetailsModel orderDetailsModel = orderDetailsModels.get(position);
        holder.image.setImageResource(Integer.parseInt(orderDetailsModel.getOdImage()));
        holder.name.setText(orderDetailsModel.getOdpname());
        holder.brandName.setText(orderDetailsModel.getOdpbrandName());
        holder.color.setText(orderDetailsModel.getOdpcolor());
        holder.size.setText(orderDetailsModel.getOdpsize());
        holder.unit.setText(""+orderDetailsModel.getOdpquantity());
        holder.price.setText(""+(orderDetailsModel.getOdpprice())+"Rs");
    }

    @Override
    public int getItemCount() {
        return orderDetailsModels.size();
    }

    public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, brandName, color, size, unit, price;

        public OrderDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_ods_itemImage);
            name = itemView.findViewById(R.id.tv_ods_productName);
            brandName = itemView.findViewById(R.id.tv_ods_brandName);
            color = itemView.findViewById(R.id.tv_ods_itemcolor);
            size = itemView.findViewById(R.id.tv_ods_size);
            unit = itemView.findViewById(R.id.tv_ods_quantity);
            price = itemView.findViewById(R.id.tv_ods_price);
        }
    }
}
