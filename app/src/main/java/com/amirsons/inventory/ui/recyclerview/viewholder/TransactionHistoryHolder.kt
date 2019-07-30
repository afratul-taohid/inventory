package com.amirsons.inventory.ui.recyclerview.viewholder

import android.view.View
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.TransactionHistory
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.utils.MyUtils
import kotlinx.android.synthetic.main.item_transaction_history.view.*

/**
 * Created by Taohid on 17, July, 2019
 * Email: taohid32@gmail.com
 */
class TransactionHistoryHolder(itemView: View): BaseRecyclerViewHolder<TransactionHistory, BaseRecyclerClickListener<TransactionHistory>>(itemView) {
    
    override fun onBindView(item: TransactionHistory, position: Int, listener: BaseRecyclerClickListener<TransactionHistory>?) {

        val priceText = "${context.getString(R.string.bdt)} ${item.totalPrice}"
        
        itemView.tv_name.text = MyUtils.capitalizeFirstLatter(item.customerName)
        itemView.tv_date.text = item.date
        itemView.tv_price.text = priceText
        
        if (item.isPaid){
            
            itemView.tv_hint_unpaid.text = context.getString(R.string.paid)
            itemView.tv_hint_unpaid.background = context.getDrawable(R.drawable.background_paid)
            
        } else {

            itemView.tv_hint_unpaid.text = context.getString(R.string.unpaid)
            itemView.tv_hint_unpaid.background = context.getDrawable(R.drawable.background_unpaid)
        }
        
        enableItemViewClick(item, listener)
    }
}