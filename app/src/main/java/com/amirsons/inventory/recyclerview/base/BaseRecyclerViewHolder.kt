package com.amirsons.inventory.recyclerview.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.amirsons.inventory.utils.OnSingleClickListener

/**
 * Created by taohid on 19,February, 2019
 * Email: taohid32@gmail.com
 */

abstract class BaseRecyclerViewHolder<T, L : BaseRecyclerClickListener<T>>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var context: Context = itemView.context

    abstract fun onBindView(item: T, position: Int, listener: L?)

    fun enableItemViewClick(item: T, baseRecyclerClickListener: BaseRecyclerClickListener<T>?) {

        itemView.setOnClickListener(object : OnSingleClickListener() {

            override fun onSingleClick(v: View) {
                baseRecyclerClickListener?.onItemClickListener(item, adapterPosition)
            }
        })
    }
}
