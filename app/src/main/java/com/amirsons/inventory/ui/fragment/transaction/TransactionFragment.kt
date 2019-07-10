package com.amirsons.inventory.ui.fragment.transaction


import android.os.Bundle
import android.view.View
import com.amirsons.inventory.R
import com.amirsons.inventory.recyclerview.adapter.TabsAdapter
import com.amirsons.inventory.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_transaction.*

/**
 * A simple [BaseFragment] subclass.
 */
class TransactionFragment : BaseFragment() {

    override val contentLayout: Int
        get() = R.layout.fragment_transaction

    companion object {
        val instance: TransactionFragment
            get() {
                val fragment = TransactionFragment()
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

        val adapter = TabsAdapter(childFragmentManager)
        adapter.addFragment(UnpaidFragment.instance, "Unpaid")
        adapter.addFragment(AllFragment.instance, "All")

        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }
}
