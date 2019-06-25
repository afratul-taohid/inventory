package com.cliniva.enventory.viewholder;

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cliniva.enventory.R;

public class CustomerViewHolder extends RecyclerView.ViewHolder {
    public TextView itemName, itemAddress, itemPhone;

    public CustomerViewHolder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.item_name);
        itemAddress = itemView.findViewById(R.id.item_address);
        itemPhone = itemView.findViewById(R.id.item_phone);
    }
}
