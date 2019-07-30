package com.amirsons.inventory.ui.base

/**
 * Created by Taohid on 06, July, 2019
 * Email: taohid32@gmail.com
 */

import android.content.Context
import com.amirsons.inventory.app.InventoryApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface BasePresenter : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    val context: Context
        get() = InventoryApp.instance.applicationContext

    fun onRemoveDatabaseListener()
}
