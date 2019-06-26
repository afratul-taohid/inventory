package com.cliniva.enventory.ui.product;

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context;

import com.cliniva.enventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductPresenter implements ProductContract.Presenter {

    private ProductContract.View mProductView;

    ProductPresenter(ProductContract.View mProductView) {
        this.mProductView = mProductView;
    }

    @Override
    public Context getContext() {
        return mProductView.getApp().getApplicationContext();
    }

    @Override
    public void onLoadList() {

        List<Product> products = new ArrayList<>();

        for (int i=0; i<10; i++){
            Product product = new Product("Sun", "(A/C)", "300gsm", "20 unit");
            products.add(product);
        }
        mProductView.setListToView(products);
    }
}
