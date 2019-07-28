package com.amirsons.inventory.ui.fragment.home

import com.amirsons.inventory.app.Constant
import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.datamanager.model.TransactionSummery
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import com.amirsons.inventory.utils.MyUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


/**
 * Created by Taohid on 06, July, 2019
 * Email: taohid32@gmail.com
 */

internal interface HomeView : BaseView {
    fun onLoadRecentSaleProductList(dataList: ArrayList<Transaction>)
    fun onLoadCurrentDaySummery(todayTransactionSummery: TransactionSummery)
    fun onLoadPreviousSummery(transactionSummery: TransactionSummery)
}

internal interface HomePresenter : BasePresenter {
    fun loadRecentSaleProductList()
    fun loadCurrentDaySummery()
    fun loadPreviousSummery(from: String, to: String)
}

class HomeMvp internal constructor(private val mMainView: HomeView) : HomePresenter {

    private val todaySummeryListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {}

        override fun onDataChange(dataSnapshot: DataSnapshot) {

            val transactionSummery = TransactionSummery()

            calculateSummeryData(dataSnapshot, transactionSummery)

            mMainView.onLoadCurrentDaySummery(transactionSummery)
        }
    }

    private val previousSummeryListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {}

        override fun onDataChange(dataSnapshot: DataSnapshot) {

            val transactionSummery = TransactionSummery()

            dataSnapshot.children.forEach{
                calculateSummeryData(it, transactionSummery)
            }

            mMainView.onLoadPreviousSummery(transactionSummery)
        }
    }

    override fun loadCurrentDaySummery() {

        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION, MyUtils.currentYear, MyUtils.currentMonth, MyUtils.currentDate)
                .addValueEventListener(todaySummeryListener)
    }

    override fun loadPreviousSummery(from: String, to: String) {

        val fromDate = from.split("-")
        val toDate = to.split("-")

        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION, MyUtils.currentYear, MyUtils.currentMonth).orderByKey().startAt(fromDate[2]).endAt(toDate[2])
                .addListenerForSingleValueEvent(previousSummeryListener)
    }


    private fun calculateSummeryData(dataSnapshot: DataSnapshot, transactionSummery: TransactionSummery) {

        dataSnapshot.children.forEach { snapshot ->

            // get the transaction
            val transaction = snapshot.getValue(Transaction::class.java)

            transaction?.let {

                if (it.transactionType == Constant.TRANSACTION_SELL) {

                    transactionSummery.totalSell += it.totalPrice
                    transactionSummery.totalDue += it.due

                } else {

                    transactionSummery.totalSupply += it.totalPrice
                    transactionSummery.totalPayable += it.due
                }
            }
        }
    }

    override fun loadRecentSaleProductList() {

    }

    override fun onRemoveDatabaseListener() {
        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).removeEventListener(todaySummeryListener)
    }
}