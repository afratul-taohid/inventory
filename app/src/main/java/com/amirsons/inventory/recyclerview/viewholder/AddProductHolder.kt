package com.amirsons.inventory.recyclerview.viewholder

import android.view.View

import com.amirsons.inventory.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.datamanager.model.AddProduct
import kotlinx.android.synthetic.main.item_transaction_product.view.*

class AddProductHolder(itemView: View) : BaseRecyclerViewHolder<AddProduct, BaseRecyclerClickListener<AddProduct>>(itemView) {

    override fun onBindView(item: AddProduct, position: Int, listener: BaseRecyclerClickListener<AddProduct>?) {
        enableItemViewClick(item, listener)

        itemView.tv_add_product_name.text = item.name
        itemView.tv_add_product_type.text = item.category
    }
}
