package com.cliniva.enventory.viewholder;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.databinding.ItemUnpaidBinding;
import com.cliniva.enventory.model.Transaction;

public class UnpaidHolder extends BaseRecyclerViewHolder<Transaction, BaseRecyclerClickListener<Transaction>> {

    private ItemUnpaidBinding binding;

    public UnpaidHolder(@NonNull ViewDataBinding itemView) {
        super(itemView);
    }

    @Override
    public void onBindView(Transaction item, int position, BaseRecyclerClickListener<Transaction> listener) {
        enableItemViewClick(item, listener);
        binding.setUnpaid(item);
    }
}
