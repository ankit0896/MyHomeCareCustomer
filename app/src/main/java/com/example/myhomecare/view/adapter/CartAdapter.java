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
import com.example.myhomecare.database.CartSqliteHelper;
import com.example.myhomecare.model.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyCartAdapterViewHolder> {

    List<CartModel> modelList = new ArrayList<>();
    Context context;
    CartSqliteHelper sqliteHelper;
    OnCartBagListner onCartBagListner;

    public CartAdapter(List<CartModel> modelList, Context context, OnCartBagListner onCartBagListner) {
        this.modelList = modelList;
        this.context = context;
        this.onCartBagListner = onCartBagListner;
        sqliteHelper = new CartSqliteHelper(context);
    }

    @NonNull
    @Override
    public MyCartAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_bag_item, parent, false);
        return new MyCartAdapterViewHolder(view, onCartBagListner);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCartAdapterViewHolder holder, final int position) {
        final CartModel model = modelList.get(position);
        holder.name.setText(model.getProductName());
        holder.brandName.setText(model.getProductBrandName());
        int quantity = model.getQuantity();
        holder.quantity.setText(""+quantity);
        holder.size.setText(model.getSize());
        holder.price.setText("Rs " + (quantity * model.getOfferPrice()));
        holder.productImage.setImageResource(Integer.parseInt(model.getImage()));
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = new Integer(String.valueOf(holder.quantity.getText()));
                quantity++;
                holder.price.setText("Rs " + (quantity * model.getOfferPrice()));
                holder.quantity.setText(""+quantity);
                CartModel product1 = new CartModel();
                product1.setProductId(model.getProductId());
                product1.setQuantity(Integer.valueOf((String) holder.quantity.getText()));
                sqliteHelper.cartInsertProduct(product1,context);
                onCartBagListner.onAddClickListner(position);
            }
        });

        holder.subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = new Integer(String.valueOf(holder.quantity.getText()));
                if (quantity == 1) {
                    onCartBagListner.onDeleteProduct(position);
                } else {
                    quantity--;
                    holder.price.setText("Rs " + (quantity * model.getOfferPrice()));
                    holder.quantity.setText(""+quantity);
                    CartModel product1 = new CartModel();
                    product1.setProductId(model.getProductId());
                    product1.setQuantity(Integer.valueOf((String) holder.quantity.getText()));
                    sqliteHelper.cartInsertProduct(product1,context);
                    onCartBagListner.onSubtractClickListner(position);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyCartAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage,options,add,subtract;
        TextView name,brandName,price,quantity,size;


        public MyCartAdapterViewHolder(@NonNull View itemView, OnCartBagListner onCartBagListner) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_cart_bag_image);
            name = itemView.findViewById(R.id.tv_cart_product_name);
            brandName = itemView.findViewById(R.id.tv_cart_product_brandName);
            options = itemView.findViewById(R.id.iv_cart_product_options);
            add = itemView.findViewById(R.id.iv_cart_product_plus);
            subtract = itemView.findViewById(R.id.iv_cart_product_minus);
            price = itemView.findViewById(R.id.tv_cart_product_price);
            quantity = itemView.findViewById(R.id.tv_cart_product_quantity);
            size = itemView.findViewById(R.id.tv_cart_product_size);
        }
    }

    public interface OnCartBagListner{
        void onAddClickListner(int position);
        void onSubtractClickListner(int position);
        void onOptionClickListner(int position);
        void onDeleteProduct(int position);
    }

}
