package com.cliniva.enventory.ui.base;

/* Created by Imran Khan on 15-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.R;
import com.cliniva.enventory.app.InventoryApp;

public abstract class BaseFragment extends Fragment {

    public abstract @LayoutRes int getLayoutRes();
    private InventoryApp inventoryApp;
    public Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inventoryApp = (InventoryApp) getActivity().getApplication();
        return inflater.inflate(getLayoutRes(), container, false);
    }

    public void setApp(){
        if (mActivity != null) {
            inventoryApp = (InventoryApp) mActivity.getApplication();
        }
    }

    public InventoryApp getApp(){
        if (inventoryApp == null) setApp();
        return inventoryApp;
    };

    public void setToolbar(View view, String title, boolean isElevation){
        AppBarLayout appBarLayout = view.findViewById(R.id.appbar);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (isElevation){
            appBarLayout.setElevation(4f);
        }
        toolbar.setTitle(title);
    }
}
