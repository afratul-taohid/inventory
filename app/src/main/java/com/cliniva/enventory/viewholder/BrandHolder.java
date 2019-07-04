package com.cliniva.enventory.viewholder;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.databinding.ItemBrandBinding;
import com.cliniva.enventory.model.Brand;

public class BrandHolder extends BaseRecyclerViewHolder<Brand, BaseRecyclerClickListener<Brand>> {

    private ItemBrandBinding binding;

    public BrandHolder(@NonNull ViewDataBinding itemView) {
        super(itemView);
        this.binding = (ItemBrandBinding) itemView;
    }

    @Override
    public void onBindView(Brand item, int position, BaseRecyclerClickListener<Brand> listener) {
        enableItemViewClick(item, listener);
        binding.setBrand(item);
    }
}
