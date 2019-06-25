package com.cliniva.enventory.ui.add;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context;

public class AddPresenter implements AddContract.Presenter {

    private AddContract.View mAddView;

    public AddPresenter(AddContract.View mAddView) {
        this.mAddView = mAddView;
    }

    @Override
    public Context getContext() {
        return mAddView.getApp().getApplicationContext();
    }
}
