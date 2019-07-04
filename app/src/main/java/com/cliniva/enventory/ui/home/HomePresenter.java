package com.cliniva.enventory.ui.home;

/* Created by Imran Khan on 15-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cliniva.enventory.api.RetrofitClient;
import com.cliniva.enventory.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mMainView;

    private static final String TAG = "HomePresenter";

    HomePresenter(HomeContract.View mMainView) {
        this.mMainView = mMainView;
    }

    @Override
    public void onLoad() {

    }

    @Override
    public Context getContext() {
        return mMainView.getApp().getApplicationContext();
    }
}
