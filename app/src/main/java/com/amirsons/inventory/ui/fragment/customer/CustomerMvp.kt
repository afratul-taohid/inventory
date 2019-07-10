package com.amirsons.inventory.ui.fragment.customer

import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.Customer
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

/**
 * Created by Taohid on 06, July, 2019
 * Email: taohid32@gmail.com
 */

internal interface CustomerView : BaseView {
    fun setCustomerListToView(customerList: ArrayList<Customer>)
}

internal interface CustomerPresenter : BasePresenter {
    fun onLoadCustomerList()
    fun onAddCustomerClick(customer: Customer)
}

class CustomerMvp internal constructor(private val mCustomerView: CustomerView) : CustomerPresenter {

    override fun onAddCustomerClick(customer: Customer) {

        // add new customer into database
        DatabaseManager.add(customer, DatabaseNode.CUSTOMER)
    }

    override fun onLoadCustomerList() {

        DatabaseManager.getDatabaseRef(DatabaseNode.CUSTOMER).addValueEventListener(object : ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val customerList = ArrayList<Customer>()

                dataSnapshot.children.forEach {
                    val customer = it.getValue(Customer::class.java)
                    customer?.let { customerList.add(customer) }
                }

                if (customerList.isNotEmpty()) {

                    mCustomerView.setCustomerListToView(customerList)
                }
            }
        })
    }
}