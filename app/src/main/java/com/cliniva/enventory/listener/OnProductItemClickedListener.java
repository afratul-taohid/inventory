package com.cliniva.enventory.listener;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.view.View;

import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.model.Product;

public interface OnProductItemClickedListener extends BaseRecyclerClickListener<Product> {
    void onSingleViewClicked(View v, int position);
}
