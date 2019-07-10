package com.amirsons.inventory.recyclerview.viewholder

import android.view.View
import com.amirsons.inventory.datamanager.model.Supplier
import com.amirsons.inventory.event.OnSupplierItemClickedListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import kotlinx.android.synthetic.main.item_customer.view.tv_item_amount
import kotlinx.android.synthetic.main.item_customer.view.tv_item_invoice_date
import kotlinx.android.synthetic.main.item_supplier.view.*

class SupplierHolder(itemView: View) : BaseRecyclerViewHolder<Supplier, OnSupplierItemClickedListener>(itemView) {

    override fun onBindView(item: Supplier, position: Int, listener: OnSupplierItemClickedListener?) {

        itemView.tv_supplier_name.text = item.name
        itemView.tv_item_amount.text = item.payableAmount.toString()
        itemView.tv_item_invoice_date.text = item.lastInvoiceDate

        enableItemViewClick(item, listener)
    }
}
