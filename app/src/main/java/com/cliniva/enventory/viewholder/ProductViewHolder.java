package com.cliniva.enventory.viewholder;

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cliniva.enventory.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView itemName, itemCategory, itemSubCategory;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.item_name);
        itemCategory = itemView.findViewById(R.id.item_category);
        itemSubCategory = itemView.findViewById(R.id.item_sub_category);
    }
}
