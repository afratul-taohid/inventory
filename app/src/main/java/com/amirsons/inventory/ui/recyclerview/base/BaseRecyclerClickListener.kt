package com.amirsons.inventory.ui.recyclerview.base

/**
 * Created by taohid on 19,February, 2019
 * Email: taohid32@gmail.com
 */

interface BaseRecyclerClickListener<T> {
    fun onItemClickListener(item: T, position: Int)
}
