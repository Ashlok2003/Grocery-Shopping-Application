package com.rajendra.onlinedailygroceries.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajendra.onlinedailygroceries.R;
import com.rajendra.onlinedailygroceries.model.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartItem> cartItemList;

    public CartAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.cartProductName.setText(cartItem.getProductName());
        holder.cartProductDesc.setText(cartItem.getProductDesc());
        holder.cartProductPrice.setText(cartItem.getProductPrice());
        holder.cartProductQty.setText(cartItem.getProductQty());
        holder.cartProductUnit.setText(cartItem.getProductUnit());

        // Load product image using Glide library
        Glide.with(holder.itemView.getContext())
                .load(cartItem.getImageResourceId())
                .placeholder(R.drawable.buy_now_bg) // Placeholder image while loading
                .error(R.drawable.button_bg) // Error image if loading fails
                .into(holder.cartProductImage);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cartProductName, cartProductDesc, cartProductPrice, cartProductQty, cartProductUnit;
        ImageView cartProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartProductName = itemView.findViewById(R.id.cart_product_name);
            cartProductDesc = itemView.findViewById(R.id.cart_product_desc);
            cartProductPrice = itemView.findViewById(R.id.cart_product_price);
            cartProductQty = itemView.findViewById(R.id.cart_product_qty);
            cartProductUnit = itemView.findViewById(R.id.cart_product_unit);
            cartProductImage = itemView.findViewById(R.id.cart_product_image);
        }
    }

}
