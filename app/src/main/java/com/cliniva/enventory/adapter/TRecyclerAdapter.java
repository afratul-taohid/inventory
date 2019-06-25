package com.cliniva.enventory.adapter;

/* Created by Imran Khan on 5/26/2019.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class TRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> itemList;

    protected TRecyclerAdapter(List<T> itemList) {
        this.itemList = itemList;
    }

    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent);
    public abstract void onBindData(RecyclerView.ViewHolder holder, T model);

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return setViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindData(holder, itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
