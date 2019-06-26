package com.cliniva.enventory.ui.add;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context;

import com.cliniva.enventory.model.AddProduct;

import java.util.ArrayList;
import java.util.List;

public class AddPresenter implements AddContract.Presenter {

    private AddContract.View mAddView;

    AddPresenter(AddContract.View mAddView) {
        this.mAddView = mAddView;
    }

    @Override
    public Context getContext() {
        return mAddView.getApp().getApplicationContext();
    }

    @Override
    public void onLoadList() {
        List<AddProduct> products = new ArrayList<>();

        for (int i=0; i<1; i++){
            AddProduct product = new AddProduct("Sun", "(A/C)", "300gsm", 3, 200, 200*3);
            products.add(product);
        }
        mAddView.setListToView(products);
    }
}
