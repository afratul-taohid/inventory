package com.amirsons.inventory.ui.fragment.supplies


import com.amirsons.inventory.app.Constant
import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.Supplier
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.datamanager.model.TransactionHistory
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

/**
 * Created by Taohid on 07, July, 2019
 * Email: taohid32@gmail.com
 */

internal interface SuppliesView : BaseView {
    fun setListToView(transactionHistoryList: ArrayList<TransactionHistory>)
}

internal interface SuppliesPresenter : BasePresenter {
    fun onLoadList()
}

class SuppliesMvp internal constructor(private val mSuppliesView: SuppliesView) : SuppliesPresenter {

    private val suppliesListLoadListener = object : ValueEventListener {

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
                    DatabaseManager.getDatabaseRef(DatabaseNode.SUPPLIER, transaction?.customerOrSupplierId!!).addListenerForSingleValueEvent(object : ValueEventListener {

                        override fun onCancelled(p0: DatabaseError) {}

                        override fun onDataChange(p0: DataSnapshot) {

                            // get the customer object
                            val supplier = p0.getValue(Supplier::class.java)

                            // create history model after found customer details
                            val transactionHistory = TransactionHistory()
                            transactionHistory.date = transaction.date
                            transactionHistory.totalPrice = transaction.totalPrice
                            transactionHistory.isPaid = transaction.due == 0
                            transactionHistory.id = it.key
                            transactionHistory.customerName = supplier?.name

                            // add the history to list
                            transactionHistoryList.add(transactionHistory)

                            // after get all items pass history list to view presenter
                            if (index == dataSnapshot.childrenCount.toInt() - 1)
                                mSuppliesView.setListToView(transactionHistoryList)
                        }
                    })
                }

            } else {

                mSuppliesView.setListToView(transactionHistoryList)
            }
        }
    }

    override fun onLoadList() {
        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).orderByChild(DatabaseNode.TRANSACTION_TYPE).equalTo(Constant.TRANSACTION_BUY)
                .addValueEventListener(suppliesListLoadListener)
    }

    override fun onRemoveDatabaseListener() {
        // remove this listener from context after activity / fragment detach
        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).removeEventListener(suppliesListLoadListener)
    }
}