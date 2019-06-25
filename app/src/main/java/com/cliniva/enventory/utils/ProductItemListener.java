package com.cliniva.enventory.utils;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.view.View;

import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.model.Product;

public interface ProductItemListener extends BaseRecyclerClickListener<Product> {
    void onButtonClick(View v, int position);
}
