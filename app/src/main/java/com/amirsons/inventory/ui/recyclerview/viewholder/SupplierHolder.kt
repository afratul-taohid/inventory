package com.amirsons.inventory.ui.recyclerview.viewholder

import android.view.View
import com.amirsons.inventory.datamanager.model.Supplier
import com.amirsons.inventory.event.OnSupplierItemClickedListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.utils.MyUtils
import kotlinx.android.synthetic.main.item_supplier.view.*

class SupplierHolder(itemView: View) : BaseRecyclerViewHolder<Supplier, OnSupplierItemClickedListener>(itemView) {

    override fun onBindView(item: Supplier, position: Int, listener: OnSupplierItemClickedListener?) {

        itemView.tv_supplier_name.text = MyUtils.capitalizeFirstLatter(item.name)
        itemView.tv_item_amount.text = item.payableAmount.toString()
        itemView.tv_item_invoice_date.text = item.lastInvoiceDate
        itemView.tv_mobile_number.text = item.mobile

        enableItemViewClick(item, listener)
    }
}
