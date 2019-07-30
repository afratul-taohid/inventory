package com.amirsons.inventory.ui.recyclerview.viewholder

import android.view.View
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.RecentSaleProduct
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.utils.MyUtils
import com.amirsons.inventory.utils.stringspan.SpanSection
import com.amirsons.inventory.utils.stringspan.StringSpanBuilder
import kotlinx.android.synthetic.main.item_recent_sale_item.view.*

class RecentSaleProductHolder(itemView: View) : BaseRecyclerViewHolder<RecentSaleProduct, BaseRecyclerClickListener<RecentSaleProduct>>(itemView) {

    override fun onBindView(item: RecentSaleProduct, position: Int, listener: BaseRecyclerClickListener<RecentSaleProduct>?) {

        val stringSpanBuilder = StringSpanBuilder.instance

        val brandSection = SpanSection.getInstance(MyUtils.capitalizeFirstLatter(item.brand))
                .setTextSize(SpanSection.TEXT_SIZE_MEDIUM)
        val categorySection = SpanSection.getInstance(item.category)
                .setTextSize(SpanSection.TEXT_SIZE_NORMAL)
                .setTextColor(context.resources.getColor(R.color.text_orange))
        val weightSection = SpanSection.getInstance(" (${item.weight})").setTextSize(SpanSection.TEXT_SIZE_SMALL)

        stringSpanBuilder.append(brandSection)
                .append(weightSection)
                .appendNewLine()
                .append(categorySection)
        stringSpanBuilder.buildWithTextView(itemView.tv_header_info)

        val saleUnit = "${item.todayTotalSale} unit"
        itemView.tv_sale_unit.text = saleUnit

        val availableUnit = "${item.availableStock} unit"
        itemView.tv_available_unit.text = availableUnit
    }
}