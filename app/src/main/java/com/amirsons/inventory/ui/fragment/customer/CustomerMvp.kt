package com.amirsons.inventory.ui.fragment.customer

import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.Customer
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Taohid on 06, July, 2019
 * Email: taohid32@gmail.com
 */

internal interface CustomerView : BaseView {
    fun setCustomerListToView(customerList: ArrayList<Customer>)
}

internal interface CustomerPresenter : BasePresenter {
    fun onLoadCustomerList() : Job
    fun onAddCustomerClick(customer: Customer)
}

class CustomerMvp internal constructor(private val mCustomerView: CustomerView) : CustomerPresenter {

    private val customerLoadListener = object : ValueEventListener{

        override fun onCancelled(p0: DatabaseError) {
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {

            val customerList = ArrayList<Customer>()

            dataSnapshot.children.forEach {
                val customer = it.getValue(Customer::class.java)
                customer?.id = it.key
                customer?.let {
                    customerList.add(customer)
                }
            }

            mCustomerView.setCustomerListToView(customerList)
        }
    }

    override fun onAddCustomerClick(customer: Customer) {

        // add new customer into database
        DatabaseManager.add(customer, DatabaseNode.CUSTOMER)
    }

    override fun onLoadCustomerList() = launch {
        DatabaseManager.getDatabaseRef(DatabaseNode.CUSTOMER).addValueEventListener(customerLoadListener)
    }

    override fun onRemoveDatabaseListener() {
        DatabaseManager.getDatabaseRef(DatabaseNode.CUSTOMER).removeEventListener(customerLoadListener)
    }
}