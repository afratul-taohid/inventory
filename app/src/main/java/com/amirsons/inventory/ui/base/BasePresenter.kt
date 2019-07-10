package com.amirsons.inventory.ui.base

/* Created by Imran Khan on 15-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context
import com.amirsons.inventory.app.InventoryApp

interface BasePresenter {
    val context: Context
        get() = InventoryApp.instance.applicationContext
}
