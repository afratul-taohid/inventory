package com.cliniva.enventory.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cliniva.enventory.R;

public class AvailableProductListViewHolder extends RecyclerView.ViewHolder {

    public TextView productName;
    public TextView productType;
    public TextView productWeight;
    public ImageButton imageButton;

    public AvailableProductListViewHolder(@NonNull View itemView) {
        super(itemView);

        productName = itemView.findViewById(R.id.tv_product_name);
        productType = itemView.findViewById(R.id.tv_product);
        productWeight = itemView.findViewById(R.id.tv_product_weight);
        imageButton = itemView.findViewById(R.id.ib_available_product_done);
    }

}
