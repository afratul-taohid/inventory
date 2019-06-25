package com.cliniva.enventory.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.adapter.base.BaseItemType;
import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taohid on 19,February, 2019
 * Email: taohid32@gmail.com
 */
public abstract class RecyclerViewAdapter<T, L extends BaseRecyclerClickListener<T>> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T, L>> {

    private List<T> items;
    private L listener;

    public RecyclerViewAdapter(List<T> items) {
        this.items = items;
    }

    public RecyclerViewAdapter() {
        // create empty array list
        this.items = new ArrayList<>();
    }

    @Override
    @NonNull
    public abstract BaseRecyclerViewHolder<T, L> onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<T, L> holder, int position) {
        holder.onBindView(getItem(position), position, listener);
    }

    /**
     * Set click listener, which must extend {@link BaseRecyclerClickListener}
     * @param listener click listener
     */
    public void setListener(L listener) {
        this.listener = listener;
    }

    /**
     * Inflates a view.
     * @param layout layout to me inflater
     * @param parent container where to inflate
     * @return inflated View
     */
    @NonNull
    protected View inflate(@LayoutRes final int layout, final @NonNull ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    @Override
    public int getItemViewType(final int position) {

        if (getItem(position) instanceof BaseItemType){
            return ((BaseItemType) getItem(position)).getType();
        } else {
            return 0;
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return this.items != null ? this.items.size() : 0;
    }

    /**
     * Sets items to the adapter and notifies that data set has been changed.
     * @param items items to set to the adapter
     */
    public void setItems(List<T> items) {

        if (items == null) {
            throw new IllegalArgumentException("Cannot set `null` item to the Recycler adapter");
        }

        this.items.clear();
        this.items.addAll(items);

        notifyDataSetChanged();
    }

    /**
     * @return All of items in this adapter.
     */
    public List<T> getItems() {
        return this.items;
    }

    /**
     * @return an items from the data set at a certain position.
     */
    public T getItem(int position) {
        return this.items.get(position);
    }

    /**
     * Adds item to the end of the data set.
     * Notifies that item has been inserted.
     * @param item item which has to be added to the adapter.
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the Recycler adapter");
        }
        this.items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    /**
     * Adds item to the end of the data set.
     * Notifies that item has been inserted.
     * @param item item which has to be added to the adapter.
     */
    public void add(T item, int position) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the Recycler adapter");
        }
        this.items.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * Adds list of items to the end of the adapter's data set.
     * Notifies that item has been inserted.
     * @param items items which has to be added to the adapter.
     */
    public void addAll(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("Cannot add `null` items to the Recycler adapter");
        }
        this.items.addAll(items);
        notifyItemRangeInserted(this.items.size() - items.size(), items.size());
    }

    /**
     * Removes an item from the adapter.
     * Notifies that item has been removed.
     * @param item to be removed
     */
    public void remove(T item) {
        int position = items.indexOf(item);
        if (position > -1) {
            this.items.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * Removes an item from the adapter.
     * Notifies that item has been removed.
     * @param position to be removed
     */
    public void remove(int position) {
        if (position > -1) {
            this.items.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * Clears all the items in the adapter.
     */
    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }
}
