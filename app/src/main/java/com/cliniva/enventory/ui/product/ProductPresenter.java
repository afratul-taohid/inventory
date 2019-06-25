package com.cliniva.enventory.ui.product;

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context;

public class ProductPresenter implements ProductContract.Presenter {

    private ProductContract.View mProductView;

    public ProductPresenter(ProductContract.View mProductView) {
        this.mProductView = mProductView;
    }

    @Override
    public Context getContext() {
        return mProductView.getApp().getApplicationContext();
    }
}
