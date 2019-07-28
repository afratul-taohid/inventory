package com.amirsons.inventory.ui.fragment.supplies


import com.amirsons.inventory.app.Constant
import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.Supplier
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.datamanager.model.TransactionHistory
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import com.amirsons.inventory.utils.MyUtils
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

        override fun onDataChange(monthSnapshot: DataSnapshot) {

            // create empty list for transaction history
            val transactionHistoryList = ArrayList<TransactionHistory>()

            // if snapshot has children
            if (monthSnapshot.hasChildren()) {

                monthSnapshot.children.forEach { dateSnapshot ->

                    dateSnapshot.children.toList().filter { it.getValue(Transaction::class.java)?.transactionType == Constant.TRANSACTION_BUY }.let {

                        it.forEachIndexed { index, transactionSnapshot ->

                            // get the transaction
                            val transaction = transactionSnapshot.getValue(Transaction::class.java)

                            // get customer details for this transaction
                            DatabaseManager.getDatabaseRef(DatabaseNode.SUPPLIER, transaction?.customerOrSupplierId!!).addListenerForSingleValueEvent(object : ValueEventListener {

                                override fun onCancelled(p0: DatabaseError) {}

                                override fun onDataChange(p0: DataSnapshot) {

                                    // get the customer object
                                    val supplier = p0.getValue(Supplier::class.java)

                                    // create history model after found customer details
                                    val transactionHistory = TransactionHistory()
                                    transactionHistory.date = "${MyUtils.currentYear}-${MyUtils.currentMonth}-${dateSnapshot.key}"
                                    transactionHistory.totalPrice = transaction.totalPrice
                                    transactionHistory.isPaid = transaction.due == 0
                                    transactionHistory.id = transactionSnapshot.key
                                    transactionHistory.customerName = supplier?.name

                                    // add the history to list
                                    transactionHistoryList.add(transactionHistory)

                                    // after get all items pass history list to view presenter
                                    if (index == it.size - 1)
                                        mSuppliesView.setListToView(transactionHistoryList)
                                }
                            })
                        }

                    }
                }

            } else {

                mSuppliesView.setListToView(transactionHistoryList)
            }
        }
    }

    override fun onLoadList() {

//        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).orderByChild(DatabaseNode.TRANSACTION_TYPE).equalTo(Constant.TRANSACTION_BUY)
//                .addValueEventListener(suppliesListLoadListener)

        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION, MyUtils.currentYear, MyUtils.currentMonth).addValueEventListener(suppliesListLoadListener)

    }

    override fun onRemoveDatabaseListener() {
        // remove this listener from context after activity / fragment detach
        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).removeEventListener(suppliesListLoadListener)
    }
}