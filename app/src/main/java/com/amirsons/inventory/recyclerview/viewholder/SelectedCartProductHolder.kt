package com.amirsons.inventory.recyclerview.viewholder

import android.view.View
import com.amirsons.inventory.R

import com.amirsons.inventory.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.datamanager.model.ProductCart
import kotlinx.android.synthetic.main.item_transaction_cart_product.view.*

class SelectedCartProductHolder(itemView: View) : BaseRecyclerViewHolder<ProductCart, BaseRecyclerClickListener<ProductCart>>(itemView) {

    override fun onBindView(item: ProductCart, position: Int, listener: BaseRecyclerClickListener<ProductCart>?) {

        val pricePerUnit = "${context.getString(R.string.bdt)} ${item.sellingPrice}"
        val productCategory = "(${item.product?.category})"
        val totalPrice = "${context.getString(R.string.bdt)} ${item.sellingPrice.times(item.quantity)}"

        itemView.tv_product_brand_name.text = item.product?.brand
        itemView.tv_product_category.text = productCategory
        itemView.tv_product_weight.text = item.product?.weight
        itemView.tv_product_unit.text = item.quantity.toString()
        itemView.tv_product_price_per_unit.text = pricePerUnit
        itemView.tv_product_total_price.text = totalPrice

        enableItemViewClick(item, listener)
    }
}
