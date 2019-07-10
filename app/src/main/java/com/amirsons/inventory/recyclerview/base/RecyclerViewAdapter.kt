package com.amirsons.inventory.recyclerview.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created by taohid on 19,February, 2019
 * Email: taohid32@gmail.com
 */
abstract class RecyclerViewAdapter<T, L : BaseRecyclerClickListener<T>>(items: ArrayList<T>? = null) : RecyclerView.Adapter<BaseRecyclerViewHolder<T, L>>() {

    private var items: ArrayList<T> = items ?: ArrayList()
    private var listener: L? = null

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<T, L>

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<T, L>, position: Int) {
        holder.onBindView(getItem(position), position, listener)
    }

    /**
     * Set click listener, which must extend [BaseRecyclerClickListener]
     * @param listener click listener
     */
    fun setListener(listener: L) {
        this.listener = listener
    }

    /**
     * Inflates a view.
     * @param layout layout to me inflater
     * @param parent container where to inflate
     * @return inflated View
     */
    protected fun inflate(@LayoutRes layout: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }

    override fun getItemViewType(position: Int): Int {

        return if (getItem(position) is BaseItemType) {
            (getItem(position) as BaseItemType).type.toInt()
        } else {
            0
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return this.items.size
    }

    /**
     * Sets items to the adapter and notifies that data set has been changed.
     * @param items items to set to the adapter
     */
    fun setItems(items: List<T>?) {

        if (items == null) {
            throw IllegalArgumentException("Cannot set `null` item to the Recycler adapter")
        }

        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }

    /**
     * @return All of items in this adapter.
     */
    fun getItems(): List<T>? {
        return this.items
    }

    /**
     * @return an items from the data set at a certain position.
     */
    fun getItem(position: Int): T {
        return this.items[position]
    }

    /**
     * Adds item to the end of the data set.
     * Notifies that item has been inserted.
     * @param item item which has to be added to the adapter.
     */
    fun add(item: T?) {
        if (item == null) {
            throw IllegalArgumentException("Cannot add null item to the Recycler adapter")
        }
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    /**
     * Adds item to the end of the data set.
     * Notifies that item has been inserted.
     * @param item item which has to be added to the adapter.
     */
    fun add(item: T?, position: Int) {
        if (item == null) {
            throw IllegalArgumentException("Cannot add null item to the Recycler adapter")
        }
        this.items.add(position, item)
        notifyItemInserted(position)
    }

    /**
     * Adds list of items to the end of the adapter's data set.
     * Notifies that item has been inserted.
     * @param items items which has to be added to the adapter.
     */
    fun addAll(items: List<T>?) {
        if (items == null) {
            throw IllegalArgumentException("Cannot add `null` items to the Recycler adapter")
        }
        this.items.addAll(items)
        notifyItemRangeInserted(this.items.size - items.size, items.size)
    }

    /**
     * Removes an item from the adapter.
     * Notifies that item has been removed.
     * @param item to be removed
     */
    fun remove(item: T) {
        val position = items.indexOf(item)
        if (position > -1) {
            this.items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * Removes an item from the adapter.
     * Notifies that item has been removed.
     * @param position to be removed
     */
    fun remove(position: Int) {
        if (position > -1) {
            this.items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * Clears all the items in the adapter.
     */
    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }
}
