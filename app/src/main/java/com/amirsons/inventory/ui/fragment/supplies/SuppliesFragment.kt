package com.amirsons.inventory.ui.fragment.supplies


import android.os.Bundle
import android.view.View

import com.amirsons.inventory.R
import com.amirsons.inventory.ui.base.BaseFragment

/**
 * A simple [BaseFragment] subclass.
 */
class SuppliesFragment : BaseFragment() {

    override val contentLayout: Int
        get() = R.layout.fragment_supplies

    companion object {

        val instance: SuppliesFragment
            get() {
                val fragment = SuppliesFragment()
                val args = Bundle()
                fragment.arguments = args
                return fragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
