package com.cliniva.enventory.ui.add;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context;

import com.cliniva.enventory.model.AddProduct;
import com.cliniva.enventory.model.Brand;
import com.cliniva.enventory.model.Product;

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

    @Override
    public void onLoadAvailable() {
        List<Product> products = new ArrayList<>();
        for (int i=0; i<3; i++){
            Product product = new Product("(A/C)", "300gsm", "20 unit", "22x18", "21/06/2019");
            products.add(product);
        }
        mAddView.setAvailableList(products);
    }

    @Override
    public void onLoadBrand() {
        List<Brand> brands = new ArrayList<>();
        for (int i=0; i<10; i++){
            Brand brand = new Brand("Panda");
            brands.add(brand);
        }
        mAddView.setBrandList(brands);
    }
}
