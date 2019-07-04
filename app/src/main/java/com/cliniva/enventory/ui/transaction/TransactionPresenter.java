package com.cliniva.enventory.ui.transaction;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cliniva.enventory.api.RetrofitClient;
import com.cliniva.enventory.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionPresenter implements TransactionContract.Presenter {

    private TransactionContract.View mTransactionView;
    private static final String TAG = "TransactionPresenter";

    TransactionPresenter(TransactionContract.View mTransactionView) {
        this.mTransactionView = mTransactionView;
    }

    @Override
    public void onLoad() {
        RetrofitClient.getInstance().getDataSet().enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(@NonNull Call<List<Transaction>> call, @NonNull Response<List<Transaction>> response) {
                mTransactionView.setList(response.body());
                Log.wtf(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(@NonNull Call<List<Transaction>> call, @NonNull Throwable t) {
                mTransactionView.showToast(t.getMessage());
                Log.wtf(TAG, t.getMessage());
            }
        });
    }

    @Override
    public Context getContext() {
        return mTransactionView.getApp().getApplicationContext();
    }
}
