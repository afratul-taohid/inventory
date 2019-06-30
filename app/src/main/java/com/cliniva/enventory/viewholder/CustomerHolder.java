package com.cliniva.enventory.viewholder;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.databinding.ItemCustomerBinding;
import com.cliniva.enventory.event.OnCustomerItemClickedListener;
import com.cliniva.enventory.model.Customer;

public class CustomerHolder extends BaseRecyclerViewHolder<Customer, OnCustomerItemClickedListener> {

    private ItemCustomerBinding binding;

    public CustomerHolder(@NonNull ViewDataBinding itemView) {
        super(itemView);
        this.binding = (ItemCustomerBinding) itemView;
    }

    @Override
    public void onBindView(Customer item, int position, OnCustomerItemClickedListener listener) {
        enableItemViewClick(item, listener);
        binding.setCustomer(item);
    }
}
