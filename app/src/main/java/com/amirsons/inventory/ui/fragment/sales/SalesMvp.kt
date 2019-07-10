package com.amirsons.inventory.ui.fragment.sales

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import java.util.*

internal interface SalesView : BaseView {
    fun setListToView(productList: ArrayList<Product>)
}

internal interface SalesPresenter : BasePresenter {
    fun onLoadList()
}

class SalesMvp internal constructor(private val mProductView: SalesView) : SalesPresenter {

    override fun onLoadList() {

        val products = ArrayList<Product>()

        for (i in 0..9) {
            val product = Product("(A/C)", "300gsm", "20 availableStock", "22x18", "21/06/2019")
            products.add(product)
        }

        mProductView.setListToView(products)
    }
}
