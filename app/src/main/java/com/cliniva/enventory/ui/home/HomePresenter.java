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
        Log.wtf(TAG, "onLoad: ");
        RetrofitClient.getInstance().getDataSet().enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(@NonNull Call<List<Transaction>> call, @NonNull Response<List<Transaction>> response) {
                mMainView.setList(response.body());
                Log.wtf(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(@NonNull Call<List<Transaction>> call, @NonNull Throwable t) {
                Log.wtf(TAG, t.getMessage());
            }
        });
    }

    @Override
    public Context getContext() {
        return mMainView.getApp().getApplicationContext();
    }
}
