package com.cliniva.enventory.ui.customer;

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context;

import com.cliniva.enventory.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerPresenter implements CustomerContract.Presenter {

    private CustomerContract.View mCustomerView;

    public CustomerPresenter(CustomerContract.View mCustomerView) {
        this.mCustomerView = mCustomerView;
    }

    @Override
    public Context getContext() {
        return mCustomerView.getApp().getApplicationContext();
    }

    @Override
    public void onLoadList() {
        List<Customer> customerList = new ArrayList<>();
        for (int i=0; i<10; i++){
            Customer customer = new Customer("Alam trading", "30৳", "25/4/2019");
            customerList.add(customer);
        }
        mCustomerView.setListToView(customerList);
    }
}
