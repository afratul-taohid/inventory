package com.cliniva.enventory.ui.inventory;

import android.content.Context;

public class InventoryPresenter implements InventoryContract.Presenter {

    private InventoryContract.View mInventoryView;

    public InventoryPresenter(InventoryContract.View mInventoryView) {
        this.mInventoryView = mInventoryView;
    }

    @Override
    public void onLoadList() {

    }

    @Override
    public Context getContext() {
        return mInventoryView.getApp().getApplicationContext();
    }
}
