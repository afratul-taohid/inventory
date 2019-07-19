package com.amirsons.inventory.ui.fragment.sales

/**
 * Created by Taohid on 07, July, 2019
 * Email: taohid32@gmail.com
 */

import com.amirsons.inventory.app.Constant
import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.Customer
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.datamanager.model.TransactionHistory
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

internal interface SalesView : BaseView {
    fun setListToView(transactionHistoryList: ArrayList<TransactionHistory>)
}

internal interface SalesPresenter : BasePresenter {
    fun onLoadList()
}

class SalesMvp internal constructor(private val mSalesView: SalesView) : SalesPresenter {

    private val salesListLoadListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {}

        override fun onDataChange(dataSnapshot: DataSnapshot) {

            // create empty list for transaction history
            val transactionHistoryList = ArrayList<TransactionHistory>()

            // if snapshot has children
            if (dataSnapshot.hasChildren()) {

                dataSnapshot.children.forEachIndexed { index, it ->

                    // get the transaction
                    val transaction = it.getValue(Transaction::class.java)

                    // get customer details for this transaction
                    DatabaseManager.getDatabaseRef(DatabaseNode.CUSTOMER, transaction?.customerOrSupplierId!!).addListenerForSingleValueEvent(object : ValueEventListener {

                        override fun onCancelled(p0: DatabaseError) {}

                        override fun onDataChange(p0: DataSnapshot) {

                            // get the customer object
                            val customer = p0.getValue(Customer::class.java)

                            // create history model after found customer details
                            val transactionHistory = TransactionHistory()
                            transactionHistory.date = transaction.date
                            transactionHistory.totalPrice = transaction.totalPrice
                            transactionHistory.isPaid = transaction.due == 0
                            transactionHistory.id = it.key
                            transactionHistory.customerName = customer?.name

                            // add the history to list
                            transactionHistoryList.add(transactionHistory)

                            // after get all items pass history list to view presenter
                            if (index == dataSnapshot.childrenCount.toInt() - 1)
                                mSalesView.setListToView(transactionHistoryList)
                        }
                    })
                }

            } else {

                mSalesView.setListToView(transactionHistoryList)
            }
        }
    }

    override fun onLoadList() {

        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).orderByChild(DatabaseNode.TRANSACTION_TYPE).equalTo(Constant.TRANSACTION_SELL)
                .addValueEventListener(salesListLoadListener)
    }

    override fun onRemoveDatabaseListener() {
        // remove this listener from context after activity / fragment detach
        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).removeEventListener(salesListLoadListener)
    }
}
