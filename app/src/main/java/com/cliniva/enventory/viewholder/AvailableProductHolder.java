package com.cliniva.enventory.viewholder;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.databinding.ItemAddProductBinding;
import com.cliniva.enventory.databinding.ItemAvailableProductBinding;
import com.cliniva.enventory.model.AddProduct;
import com.cliniva.enventory.model.Product;

public class AvailableProductHolder extends BaseRecyclerViewHolder<Product, BaseRecyclerClickListener<Product>> {

    private ItemAvailableProductBinding binding;

    public AvailableProductHolder(@NonNull ViewDataBinding itemView) {
        super(itemView);
        this.binding = (ItemAvailableProductBinding) itemView;
    }

    @Override
    public void onBindView(Product item, int position, BaseRecyclerClickListener<Product> listener) {
        enableItemViewClick(item, listener);
        binding.setProduct(item);
    }
}
