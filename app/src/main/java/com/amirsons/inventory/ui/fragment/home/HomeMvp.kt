package com.amirsons.inventory.ui.fragment.home

import com.amirsons.inventory.app.Constant
import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.datamanager.model.RecentSaleProduct
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.datamanager.model.TransactionSummery
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import com.amirsons.inventory.utils.MyUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * Created by Taohid on 06, July, 2019
 * Email: taohid32@gmail.com
 */

internal interface HomeView : BaseView {

    fun onLoadRecentSaleProductList(dataList: ArrayList<RecentSaleProduct>)
    fun onLoadCurrentDaySummery(todayTransactionSummery: TransactionSummery)
    fun onLoadCurrentWeekSummery(transactionSummery: TransactionSummery)
    fun onLoadCurrentMonthSummery(transactionSummery: TransactionSummery)
}

internal interface HomePresenter : BasePresenter {
    fun loadCurrentDaySummery() : Job
    fun loadCurrentWeekSummery() : Job
    fun loadCurrentMonthSummery() : Job
}

class HomeMvp internal constructor(private val mMainView: HomeView) : HomePresenter {

    private val todaySummeryListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {}

        override fun onDataChange(dataSnapshot: DataSnapshot) {

            // load recent sale product list
            loadRecentSaleProduct(dataSnapshot)

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

            mMainView.onLoadCurrentWeekSummery(transactionSummery)
        }
    }

    private fun loadRecentSaleProduct(dataSnapshot: DataSnapshot) = launch {

        val recentSaleProductList = ArrayList<RecentSaleProduct>()

        dataSnapshot.children.forEach {

            // get the transaction
            val transaction = it.getValue(Transaction::class.java)

            if (transaction?.transactionType == Constant.TRANSACTION_SELL) {

                transaction.products.forEachIndexed { index, productCart ->

                    DatabaseManager.getDatabaseRef(DatabaseNode.PRODUCT).child(productCart.brand!!).child(productCart.productId!!)
                            .addListenerForSingleValueEvent(object : ValueEventListener{

                                override fun onCancelled(p0: DatabaseError) {}

                                override fun onDataChange(productSnapshot: DataSnapshot) {

                                    // get product details from product node
                                    val product = productSnapshot.getValue(Product::class.java)

                                    // create recent product object
                                    var recentSaleProduct = RecentSaleProduct(productCart.brand!!, product?.category!!)

                                    if (recentSaleProductList.contains(recentSaleProduct)){

                                        // update existing
                                        recentSaleProduct = recentSaleProductList[recentSaleProductList.indexOf(recentSaleProduct)]
                                        recentSaleProduct.availableStock = product.availableStock
                                        recentSaleProduct.todayTotalSale += productCart.quantity

                                    } else {

                                        recentSaleProduct.productId = productCart.productId
                                        recentSaleProduct.availableStock = product.availableStock
                                        recentSaleProduct.weight = product.weight
                                        recentSaleProduct.todayTotalSale = productCart.quantity

                                        // add new recent sale product list
                                        recentSaleProductList.add(recentSaleProduct)
                                    }

                                    if (index == transaction.products.size - 1){
                                        mMainView.onLoadRecentSaleProductList(recentSaleProductList)
                                    }
                                }
                            })
                }
            }
        }
    }

    override fun loadCurrentDaySummery() = launch {

        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION, MyUtils.currentYear, MyUtils.currentMonth, MyUtils.currentDate)
                .addValueEventListener(todaySummeryListener)
    }

    override fun loadCurrentWeekSummery() = launch {

        // get previous 7 days summery data
        val from = MyUtils.getPreviousDateFromNow(-7)
        val to = MyUtils.getPreviousDateFromNow(-1)

        val fromDate = from.split("-")
        val toDate = to.split("-")

        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION, MyUtils.currentYear, MyUtils.currentMonth).orderByKey().startAt(fromDate[2]).endAt(toDate[2])
                .addListenerForSingleValueEvent(previousSummeryListener)
    }

    override fun loadCurrentMonthSummery() = launch {

        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION, MyUtils.currentYear, MyUtils.currentMonth)
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

    override fun onRemoveDatabaseListener() {
        DatabaseManager.getDatabaseRef(DatabaseNode.TRANSACTION).removeEventListener(todaySummeryListener)
    }
}