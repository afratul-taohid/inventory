package com.cliniva.enventory.ui.payment;

import android.content.Context;

public class PaymentPresenter implements PaymentContract.Presenter {

    private PaymentContract.View mPaymentView;

    public PaymentPresenter(PaymentContract.View mPaymentView) {
        this.mPaymentView = mPaymentView;
    }

    @Override
    public Context getContext() {
        return mPaymentView.getApp().getApplicationContext();
    }
}
