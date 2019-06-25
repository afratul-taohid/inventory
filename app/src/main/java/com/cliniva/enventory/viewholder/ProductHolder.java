package com.cliniva.enventory.viewholder;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.utils.ProductItemListener;

public class ProductHolder extends BaseRecyclerViewHolder<Product, ProductItemListener> {

    public ProductHolder(@NonNull ViewDataBinding itemView) {
        super(itemView);
    }

    @Override
    public void onBindView(Product item, int position, ProductItemListener listener) {
        enableItemViewClick(item, listener);
    }
}
