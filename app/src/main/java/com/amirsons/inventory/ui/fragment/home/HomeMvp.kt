package com.amirsons.inventory.ui.fragment.home

import android.util.Log
import com.amirsons.inventory.app.Constant
import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.TodayTransactionSummery
import com.amirsons.inventory.datamanager.model.Transaction
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
    fun setList(dataList: ArrayList<Transaction>)
    fun onLoadCurrentDaySummery(todayTransactionSummery: TodayTransactionSummery)
}

internal interface HomePresenter : BasePresenter {
    fun onLoad()
    fun loadCurrentDaySummery()
}

class HomeMvp internal constructor(private val mMainView: HomeView) : HomePresenter {

    private val todaySummeryListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {}

        override fun onDataChange(dataSnapshot: DataSnapshot) {

            val todayTransactionSummery = TodayTransactionSummery()

            Log.w("test", "${dataSnapshot.childrenCount}")

            dataSnapshot.children.forEach { snapshot ->

                // get the transaction
                val transaction = snapshot.getValue(Transaction::class.java)

                transaction?.let {

                    if (it.transactionType == Constant.TRANSACTION_SELL) {
                        todayTransactionSummery.totalSell.plus(it.totalPrice)
                        todayTransactionSummery.totalDue.plus(it.due)
                    } else {
                        todayTransactionSummery.totalSupply.plus(it.totalPrice)
                        todayTransactionSummery.totalPayable.plus(it.due)
                    }
                }
            }

            mMainView.onLoadCurrentDaySummery(todayTransactionSummery)
        }
    }

    override fun loadCurrentDaySummery() {

        val currentData = MyUtils.currentDateFormatted

        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).orderByChild(DatabaseNode.TRANSACTION_DATE).equalTo(currentData)
                .addValueEventListener(todaySummeryListener)
    }

    override fun onLoad() {
    }

    override fun onRemoveDatabaseListener() {
        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).removeEventListener(todaySummeryListener)
    }
}