package com.cliniva.enventory.viewholder;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.databinding.ItemAddProductBinding;
import com.cliniva.enventory.model.AddProduct;

public class AddProductHolder extends BaseRecyclerViewHolder<AddProduct, BaseRecyclerClickListener<AddProduct>> {

    private ItemAddProductBinding binding;

    public AddProductHolder(@NonNull ViewDataBinding itemView) {
        super(itemView);
        this.binding = (ItemAddProductBinding) itemView;
    }

    @Override
    public void onBindView(AddProduct item, int position, BaseRecyclerClickListener<AddProduct> listener) {
        enableItemViewClick(item, listener);
        binding.setAdd(item);
    }
}
