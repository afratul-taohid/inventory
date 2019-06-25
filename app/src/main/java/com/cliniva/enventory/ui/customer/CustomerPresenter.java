package com.cliniva.enventory.ui.customer;

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context;

public class CustomerPresenter implements CustomerContract.Presenter {

    private CustomerContract.View mCustomerView;

    public CustomerPresenter(CustomerContract.View mCustomerView) {
        this.mCustomerView = mCustomerView;
    }

    @Override
    public Context getContext() {
        return mCustomerView.getApp().getApplicationContext();
    }
}
