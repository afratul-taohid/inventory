package com.cliniva.enventory.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cliniva.enventory.R;

public class AddProductListViewHolder extends RecyclerView.ViewHolder {

    public TextView addProductName;
    public TextView addProductType;
    public TextView addProductWeight;
    public TextView addProductUnit;
    public TextView addProductUnitX;
    public TextView addProductPricePerUnit;
    public TextView addProductTotalPrice;

    public AddProductListViewHolder(@NonNull View itemView) {
        super(itemView);

        addProductName = itemView.findViewById(R.id.tv_add_product_name);
        addProductType = itemView.findViewById(R.id.tv_add_product_type);
        addProductWeight = itemView.findViewById(R.id.tv_add_product_weight);
        addProductUnit = itemView.findViewById(R.id.tv_add_product_unit);
        addProductUnitX = itemView.findViewById(R.id.tv_add_product_unit_x);
        addProductPricePerUnit = itemView.findViewById(R.id.tv_add_product_price_per_unit);
        addProductTotalPrice = itemView.findViewById(R.id.tv_add_product_total_price);
    }
}
