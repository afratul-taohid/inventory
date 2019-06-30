package com.cliniva.enventory.viewholder;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.databinding.ItemSupplierBinding;
import com.cliniva.enventory.event.OnSupplierItemClickedListener;
import com.cliniva.enventory.model.Customer;

public class SupplierHolder extends BaseRecyclerViewHolder<Customer, OnSupplierItemClickedListener> {

    private ItemSupplierBinding binding;

    public SupplierHolder(@NonNull ViewDataBinding itemView) {
        super(itemView);
        binding = (ItemSupplierBinding) itemView;
    }

    @Override
    public void onBindView(Customer item, int position, OnSupplierItemClickedListener listener) {
        enableItemViewClick(item, listener);
        binding.setSupplier(item);
    }
}
