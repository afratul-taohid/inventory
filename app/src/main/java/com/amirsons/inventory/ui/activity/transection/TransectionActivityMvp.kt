package com.amirsons.inventory.ui.activity.transection

import com.amirsons.inventory.datamanager.model.ProductCart
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView

/**
 * Created by Taohid on 06, July, 2019
 * Email: taohid32@gmail.com
 */

interface TransactionActivityView : BaseView {
    fun addProductToCartList(productCart: ProductCart)
}

internal interface TransactionActivityPresenter : BasePresenter {

}

class TransactionActivityMvp internal constructor(private val mTransactionView: TransactionActivityView) : TransactionActivityPresenter {

}