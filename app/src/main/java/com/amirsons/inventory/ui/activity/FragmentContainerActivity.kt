package com.amirsons.inventory.ui.activity

import android.os.Bundle
import com.amirsons.inventory.R
import com.amirsons.inventory.ui.base.BaseActivity
import com.amirsons.inventory.ui.fragment.customer.CustomerFragment
import com.amirsons.inventory.ui.fragment.supplier.SupplierFragment
import com.amirsons.inventory.ui.fragment.transaction.TransactionFragment

class FragmentContainerActivity : BaseActivity() {

    override val contentLayout: Int
        get() = R.layout.activity_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var fragmentType = 1

        if (intent.extras != null) {
            fragmentType = intent.extras!!.getInt("FRAGMENT_TYPE_EXTRA", 1)
        }

        onFragmentTransaction(fragmentType)
    }

    private fun onFragmentTransaction(fragmentType: Int) {

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        when (fragmentType) {

            1 -> transaction.replace(R.id.fl_fragment_container, TransactionFragment.instance).commit()

            2 -> transaction.replace(R.id.fl_fragment_container, CustomerFragment.instance).commit()

            3 -> transaction.replace(R.id.fl_fragment_container, SupplierFragment.instance).commit()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
