package com.amirsons.inventory.ui.recyclerview.viewholder

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.view.View
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.event.OnProductItemClickedListener
import com.amirsons.inventory.datamanager.model.Product
import kotlinx.android.synthetic.main.item_inventory.view.*

class ProductHolder(itemView: View) : BaseRecyclerViewHolder<Product, OnProductItemClickedListener>(itemView) {

    override fun onBindView(item: Product, position: Int, listener: OnProductItemClickedListener?) {

        val size = "(${item.size})"
        val availableCount = "${item.availableStock} unit"

        itemView.tv_item_category.text = item.category
        itemView.tv_item_size.text = size
        itemView.tv_item_last_supply.text = item.lastSupply
        itemView.tv_product_weight.text = item.weight
        itemView.tv_item_unit.text = availableCount

        enableItemViewClick(item, listener)
    }
}
