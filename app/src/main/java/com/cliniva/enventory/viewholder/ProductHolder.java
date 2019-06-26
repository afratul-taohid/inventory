package com.cliniva.enventory.viewholder;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.databinding.ItemProductBinding;
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.listener.OnProductItemClickedListener;

public class ProductHolder extends BaseRecyclerViewHolder<Product, OnProductItemClickedListener> {

    private ItemProductBinding binding;

    public ProductHolder(@NonNull ViewDataBinding itemView) {
        super(itemView);
        this.binding = (ItemProductBinding) itemView;
    }

    @Override
    public void onBindView(Product item, int position, OnProductItemClickedListener listener) {
        enableItemViewClick(item, listener);
        binding.setProduct(item);
    }
}
