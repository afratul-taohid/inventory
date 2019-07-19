package com.amirsons.inventory.ui.fragment.payment

import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView

internal interface PaymentView : BaseView
internal interface PaymentPresenter : BasePresenter

class PaymentMvp internal constructor(private val mPaymentView: PaymentView) : PaymentPresenter {
    override fun onRemoveDatabaseListener() {
    }
}