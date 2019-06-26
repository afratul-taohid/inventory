package com.cliniva.enventory.ui.supplier;

import android.content.Context;

import com.cliniva.enventory.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class SupplierPresenter implements SupplierContract.Presenter {

    private SupplierContract.View mSupplierView;

    SupplierPresenter(SupplierContract.View mSupplierView) {
        this.mSupplierView = mSupplierView;
    }

    @Override
    public Context getContext() {
        return mSupplierView.getApp().getApplicationContext();
    }

    @Override
    public void onLoadList() {
        List<Customer> supplierList = new ArrayList<>();
        for (int i=0; i<10; i++){
            Customer supplier = new Customer("Alam trading", "30৳", "25/4/2019");
            supplierList.add(supplier);
        }
        mSupplierView.setListToView(supplierList);
    }
}
