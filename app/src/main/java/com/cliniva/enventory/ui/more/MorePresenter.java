package com.cliniva.enventory.ui.more;

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context;

public class MorePresenter implements MoreContract.Presenter {

    private MoreContract.View mMoreView;

    public MorePresenter(MoreContract.View mMoreView) {
        this.mMoreView = mMoreView;
    }

    @Override
    public Context getContext() {
        return mMoreView.getApp().getApplicationContext();
    }
}
