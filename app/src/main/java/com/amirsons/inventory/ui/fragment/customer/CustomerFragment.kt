package com.amirsons.inventory.ui.fragment.customer


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.Customer
import com.amirsons.inventory.event.OnCustomerItemClickedListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.ui.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.ui.recyclerview.viewholder.CustomerHolder
import com.amirsons.inventory.ui.base.BaseFragment
import com.amirsons.inventory.ui.widgets.BottomSheetAddCustomer
import com.amirsons.inventory.ui.widgets.OnAddCustomerConfirm
import kotlinx.android.synthetic.main.fragment_customer.*
import java.util.*


/**
 * A simple [BaseFragment] subclass.
 */
class CustomerFragment : BaseFragment(), CustomerView {

    private lateinit var mCustomerPresenter: CustomerPresenter
    private lateinit var mCustomerAdapter: RecyclerViewAdapter<Customer, OnCustomerItemClickedListener>

    override val contentLayout: Int
        get() = R.layout.fragment_customer

    companion object {

        val instance: CustomerFragment
            get() {
                val fragment = CustomerFragment()
                val args = Bundle()
                fragment.arguments = args
                return fragment
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCustomerPresenter = CustomerMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(view, mCustomerPresenter.context.getString(R.string.action_customer), false)

        // init the customer list recyclerview adapter
        initCustomerListAdapter()

        // get all customer
        mCustomerPresenter.onLoadCustomerList()

        btn_fab.setOnClickListener {

           BottomSheetAddCustomer(context, "Add Customer Details" , object : OnAddCustomerConfirm {

                override fun onConfirm(item: Customer): Boolean {

                    // add new customer
                    mCustomerPresenter.onAddCustomerClick(item)

                    // return true to dismiss the dialog
                    return true
                }

            }).show()

        }
    }

    private fun initCustomerListAdapter() {

        mCustomerAdapter = object : RecyclerViewAdapter<Customer, OnCustomerItemClickedListener>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Customer, OnCustomerItemClickedListener> {
                return CustomerHolder(inflate(R.layout.item_customer, parent))
            }
        }

        rv_customer_list.adapter = mCustomerAdapter
    }

    override fun setCustomerListToView(customerList: ArrayList<Customer>) {
        mCustomerAdapter.setItems(customerList)
    }
}
