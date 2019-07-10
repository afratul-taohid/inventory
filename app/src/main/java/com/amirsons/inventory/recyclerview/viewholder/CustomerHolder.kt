package com.amirsons.inventory.recyclerview.viewholder

import android.view.View
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.event.OnCustomerItemClickedListener
import com.amirsons.inventory.datamanager.model.Customer
import kotlinx.android.synthetic.main.item_customer.view.*

class CustomerHolder(itemView: View) : BaseRecyclerViewHolder<Customer, OnCustomerItemClickedListener>(itemView) {

    override fun onBindView(item: Customer, position: Int, listener: OnCustomerItemClickedListener?) {

        itemView.tv_customer_name.text = item.name
        itemView.tv_item_amount.text = item.receivableAmount.toString()
        itemView.tv_item_invoice_date.text = item.lastInvoiceDate

        enableItemViewClick(item, listener)
    }
}
