package com.amirsons.inventory.ui.fragment.more

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.amirsons.inventory.R
import com.amirsons.inventory.ui.activity.FragmentContainerActivity
import com.amirsons.inventory.ui.activity.MainActivity
import com.amirsons.inventory.ui.activity.login.LoginActivity
import com.amirsons.inventory.ui.base.BaseFragment
import com.amirsons.inventory.utils.IntentUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_more.*

/**
 * A simple [BaseFragment] subclass.
 */
class MoreFragment : BaseFragment(), MoreView, View.OnClickListener {

    private lateinit var mMorePresenter: MorePresenter

    override val contentLayout: Int
        get() = R.layout.fragment_more

    companion object {

        val instance: MoreFragment
            get() {
                val fragment = MoreFragment()
                val args = Bundle()
                fragment.arguments = args
                return fragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMorePresenter = MoreMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(view, mMorePresenter.context.getString(R.string.action_more), false)

        btn_event_transaction.setOnClickListener(this)
        btn_event_customer.setOnClickListener(this)
        btn_event_supplier.setOnClickListener(this)
        tv_customers.setOnClickListener(this)
        tv_suppliers.setOnClickListener(this)

        btn_logout.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)

            activity?.finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_event_transaction -> onNavigation(1)
            R.id.btn_event_customer, R.id.tv_customers -> onNavigation(2)
            R.id.btn_event_supplier, R.id.tv_suppliers -> onNavigation(3)
        }
    }

    private fun onNavigation(extra: Int) {
        val bundle = Bundle()
        bundle.putInt("FRAGMENT_TYPE_EXTRA", extra)
        IntentUtils.instance.onActivityIntentWithExtras(context, FragmentContainerActivity::class.java, bundle)
    }
}
