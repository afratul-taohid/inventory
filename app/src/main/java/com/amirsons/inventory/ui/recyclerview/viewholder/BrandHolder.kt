package com.amirsons.inventory.ui.recyclerview.viewholder

import android.view.View
import com.amirsons.inventory.datamanager.model.BrandProduct
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.utils.MyUtils
import kotlinx.android.synthetic.main.item_brand.view.*

class BrandHolder(itemView: View) : BaseRecyclerViewHolder<BrandProduct, BaseRecyclerClickListener<BrandProduct>>(itemView) {

    override fun onBindView(item: BrandProduct, position: Int, listener: BaseRecyclerClickListener<BrandProduct>?) {

        itemView.tv_item_brand_name.text = MyUtils.capitalizeFirstLatter(item.brand)

        // change the selected background
        itemView.tv_item_brand_name.isSelected = item.isSelected
        itemView.tv_item_brand_name.elevation = if (item.isSelected) 5f else 0f

        itemView.setOnClickListener {

            if (listener != null) {

                listener.onItemClickListener(item, position)

                // change the current selected background
                itemView.tv_item_brand_name.isSelected = true
                itemView.tv_item_brand_name.elevation = 5f
            }
        }
    }
}
