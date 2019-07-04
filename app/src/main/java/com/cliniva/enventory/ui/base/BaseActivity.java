package com.cliniva.enventory.ui.base;

/* Created by Imran Khan on 15-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cliniva.enventory.R;
import com.cliniva.enventory.app.InventoryApp;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract @LayoutRes int getLayoutRes();
    public InventoryApp inventoryApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        inventoryApp = (InventoryApp) getApplication();
    }
}
