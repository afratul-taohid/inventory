package com.amirsons.inventory.ui.activity.add

import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView

/**
 * Created by Taohid on 06, July, 2019
 * Email: taohid32@gmail.com
 */

internal interface TransactionActivityView : BaseView {

}

internal interface TransactionActivityPresenter : BasePresenter {

}

class TransactionActivityMvp internal constructor(private val mTransactionView: TransactionActivityView) : TransactionActivityPresenter {

}