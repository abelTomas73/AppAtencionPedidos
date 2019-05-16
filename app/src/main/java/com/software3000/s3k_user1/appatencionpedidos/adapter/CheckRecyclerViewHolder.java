package com.software3000.s3k_user1.appatencionpedidos.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software3000.s3k_user1.appatencionpedidos.R;

public class CheckRecyclerViewHolder extends RecyclerView.ViewHolder{

    public TextView quantity, productName, productPrice, removeProduct;
    public ImageView imagen_product;
    public LinearLayout linear_producto_nombre_checkout;

    public CheckRecyclerViewHolder(View itemView) {
        super(itemView);

        linear_producto_nombre_checkout = itemView.findViewById(R.id.linear_producto_nombre_checkout);
        quantity = (TextView)itemView.findViewById(R.id.quantity);
        imagen_product = itemView.findViewById(R.id.imagen_product);
        productName =(TextView)itemView.findViewById(R.id.product_name);
        productPrice = (TextView)itemView.findViewById(R.id.product_price);
        removeProduct = (TextView)itemView.findViewById(R.id.remove_from_cart);
    }
}