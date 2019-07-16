package com.amirsons.inventory.ui.recyclerview.viewholder

import android.view.View

import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.datamanager.model.Transaction

class UnpaidHolder(itemView: View) : BaseRecyclerViewHolder<Transaction, BaseRecyclerClickListener<Transaction>>(itemView) {

    override fun onBindView(item: Transaction, position: Int, listener: BaseRecyclerClickListener<Transaction>?) {
        enableItemViewClick(item, listener)
    }
}
