package com.amirsons.inventory.recyclerview.viewholder

import android.view.View

import com.amirsons.inventory.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.datamanager.model.Transaction

class UnpaidHolder(itemView: View) : BaseRecyclerViewHolder<Transaction, BaseRecyclerClickListener<Transaction>>(itemView) {

    override fun onBindView(item: Transaction, position: Int, listener: BaseRecyclerClickListener<Transaction>?) {
        enableItemViewClick(item, listener)
    }
}
