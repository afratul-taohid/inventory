package com.amirsons.inventory.app

/* Created by Imran Khan on 15-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.app.Application

class InventoryApp : Application() {

    companion object {
       lateinit var instance: InventoryApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
