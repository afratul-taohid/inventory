package com.amirsons.inventory.recyclerview.viewholder

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.view.View

import com.amirsons.inventory.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.datamanager.model.Product

class AvailableProductHolder(itemView: View) : BaseRecyclerViewHolder<Product, BaseRecyclerClickListener<Product>>(itemView) {

    override fun onBindView(item: Product, position: Int, listener: BaseRecyclerClickListener<Product>?) {
        enableItemViewClick(item, listener)
    }
}
