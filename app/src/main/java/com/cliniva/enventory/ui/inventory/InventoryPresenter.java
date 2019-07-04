package com.cliniva.enventory.ui.inventory;

import android.content.Context;

import com.cliniva.enventory.model.Brand;
import com.cliniva.enventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryPresenter implements InventoryContract.Presenter {

    private InventoryContract.View mInventoryView;

    InventoryPresenter(InventoryContract.View mInventoryView) {
        this.mInventoryView = mInventoryView;
    }

    @Override
    public void onLoadList() {
        List<Product> products = new ArrayList<>();
        List<Brand> brands = new ArrayList<>();

        for (int i=0; i<10; i++){
            Product product = new Product("(A/C)", "300gsm", "20 unit", "22x18", "21/06/2019");
            products.add(product);
        }
        for (int i=0; i<5; i++){
            Brand brand = new Brand("Panda");
            brands.add(brand);
        }
        mInventoryView.setProductListToView(products);
        mInventoryView.setBrandListToView(brands);
    }

    @Override
    public Context getContext() {
        return mInventoryView.getApp().getApplicationContext();
    }
}
