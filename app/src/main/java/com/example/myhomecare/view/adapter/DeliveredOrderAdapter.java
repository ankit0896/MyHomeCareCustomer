package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.model.OrderDetailModel;

import java.util.ArrayList;
import java.util.List;

public class DeliveredOrderAdapter extends RecyclerView.Adapter<DeliveredOrderAdapter.OrderedDeliveredViewHolder> {

    List<OrderDetailModel> models = new ArrayList<>();
    Context context;
    OrderedDeliveredListner orderedDeliveredListner;

    public DeliveredOrderAdapter(List<OrderDetailModel> models, Context context, OrderedDeliveredListner orderedDeliveredListner) {
        this.models = models;
        this.context = context;
        this.orderedDeliveredListner = orderedDeliveredListner;
    }

    @NonNull
    @Override
    public OrderedDeliveredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_myorder_item,parent,false);
        return new OrderedDeliveredViewHolder(view,orderedDeliveredListner);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderedDeliveredViewHolder holder, int position) {
        OrderDetailModel detailModel = models.get(position);
        holder.orderNumber.setText("Order No"+detailModel.getOrderId());
        holder.date.setText(detailModel.getOrderDate());
        holder.trackingNumber.setText(detailModel.getOrderTrackingNumber());
        holder.quantity.setText(""+detailModel.getOrderQuantity());
        holder.amount.setText(""+(int)detailModel.getOrderTotalAmount()+"Rs");
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class OrderedDeliveredViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber,date,trackingNumber,quantity,amount,details;
        public OrderedDeliveredViewHolder(@NonNull View itemView, final OrderedDeliveredListner orderedDeliveredListner) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.tv_myorder_orderNumber);
            date = itemView.findViewById(R.id.tv_myorder_orderDate);
            trackingNumber = itemView.findViewById(R.id.tv_myorder_orderTrackingNumber);
            quantity = itemView.findViewById(R.id.tv_myorder_orderQuantity);
            amount = itemView.findViewById(R.id.tv_myorder_totalAmount);
            details = itemView.findViewById(R.id.tv_myorder_orderDetails);

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    orderedDeliveredListner.onDetailsClickListner(getAdapterPosition());
                }
            });

        }
    }


    public interface OrderedDeliveredListner{
        void onDetailsClickListner(int position);
    }
}
