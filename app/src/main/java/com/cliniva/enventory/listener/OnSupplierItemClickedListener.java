package com.cliniva.enventory.listener;

import android.view.View;

import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.model.Customer;

public interface OnSupplierItemClickedListener extends BaseRecyclerClickListener<Customer> {
    void onSingleViewClicked(View v, int position);
}
