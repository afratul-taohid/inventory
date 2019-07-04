package com.cliniva.enventory.ui.payment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.R;
import com.cliniva.enventory.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends BaseFragment {


    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_payment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
