package com.amirsons.inventory.ui.fragment.payment


import android.os.Bundle
import android.view.View

import com.amirsons.inventory.R
import com.amirsons.inventory.ui.base.BaseFragment

/**
 * A simple [BaseFragment] subclass.
 */
class PaymentFragment : BaseFragment(), PaymentView {

    private lateinit var presenter: PaymentPresenter

    override val contentLayout: Int
        get() = R.layout.fragment_payment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PaymentMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
