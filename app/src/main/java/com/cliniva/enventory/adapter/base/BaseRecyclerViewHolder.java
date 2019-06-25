package com.cliniva.enventory.adapter.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cliniva.enventory.utils.OnSingleClickListener;

/**
 * Created by taohid on 19,February, 2019
 * Email: taohid32@gmail.com
 */
public abstract class BaseRecyclerViewHolder<T, L extends BaseRecyclerClickListener> extends RecyclerView.ViewHolder {

    public Context context;

    public BaseRecyclerViewHolder(@NonNull ViewDataBinding itemView) {
        super(itemView.getRoot());
        this.context = itemView.getRoot().getContext();
    }

    public abstract void onBindView(T item, int position, L listener);

    public void enableItemViewClick(T item, BaseRecyclerClickListener<T> baseRecyclerClickListener){

        itemView.setOnClickListener(new OnSingleClickListener() {

            @Override
            public void onSingleClick(View v) {

                if (baseRecyclerClickListener != null){
                    baseRecyclerClickListener.onItemClickListener(item, getAdapterPosition());
                }
            }
        });
    }
}
