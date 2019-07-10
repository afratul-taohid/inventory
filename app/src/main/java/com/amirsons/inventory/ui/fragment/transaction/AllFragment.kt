package com.amirsons.inventory.ui.fragment.transaction


import android.os.Bundle
import android.view.View

import com.amirsons.inventory.R
import com.amirsons.inventory.ui.base.BaseFragment

/**
 * A simple [BaseFragment] subclass.
 */
class AllFragment : BaseFragment() {

    override val contentLayout: Int
        get() = R.layout.fragment_all

    companion object {

        val instance: AllFragment
            get() {
                val fragment = AllFragment()
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
