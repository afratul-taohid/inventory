package com.amirsons.inventory.ui.activity.transaction

import com.amirsons.inventory.app.Constant
import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.*
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

interface TransactionActivityView : BaseView {
    fun addProductToCartList(productCart: ProductCart)
    fun onTransactionComplete(success: Boolean)
    fun onCustomerSearchListLoaded(searchListItems: ArrayList<CustomerOrSupplierSearchItem>)
}

internal interface TransactionActivityPresenter : BasePresenter {
    fun onTransactionDoneClick(transaction: Transaction)
    fun loadCustomerList(transactionType: String)
}

class TransactionActivityMvp internal constructor(private val mTransactionView: TransactionActivityView) : TransactionActivityPresenter {

    override fun loadCustomerList(transactionType: String) {

        DatabaseManager.getDatabaseRef(if (transactionType == Constant.TRANSACTION_SELL) DatabaseNode.CUSTOMER else DatabaseNode.SUPPLIER).addListenerForSingleValueEvent(object : ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // items of search list
                val itemList = ArrayList<CustomerOrSupplierSearchItem>()

                // transaction type is sell, so load the customer list
                if (transactionType == Constant.TRANSACTION_SELL) {

                    dataSnapshot.children.forEach {
                        val customer = it.getValue(Customer::class.java)
                        val searchItem = CustomerOrSupplierSearchItem(it.key!!, customer?.name!!, customer.mobile!!, customer.receivableAmount)
                        itemList.add(searchItem)
                    }

                } else {

                    // transaction type is for buy, so load the supplier list
                    dataSnapshot.children.forEach {
                        val supplier = it.getValue(Supplier::class.java)
                        val searchItem = CustomerOrSupplierSearchItem(it.key!!, supplier?.name!!, supplier.mobile!!, supplier.payableAmount)
                        itemList.add(searchItem)
                    }
                }

                // pass this items to main view to load
                mTransactionView.onCustomerSearchListLoaded(itemList)
            }
        })
    }

    override fun onTransactionDoneClick(transaction: Transaction) {

        // save this new transaction to database
        DatabaseManager.add(transaction, DatabaseNode.TRANSACTION)

        // update products database based on transaction type
        // for transaction type sell, remove some products
        // or type buy, add new supply products to database
        if (transaction.transactionType == Constant.TRANSACTION_SELL) {

            transaction.products.forEach {

                // calculate new stock quantity after sell some product
                val newStockQuantity = it.product!!.availableStock.minus(it.quantity)

                // update the existing product
                val product = it.product
                product?.availableStock = newStockQuantity

                // if current retails price updated
                if (it.isUpdatePrice){
                    product?.retailPrice = it.unitPrice
                }

                // update current product new stock quantity
                DatabaseManager.update(product, DatabaseNode.PRODUCT, it.product?.brand!!, it.productId!!)
            }


            if (transaction.due > 0) {
                // add current due to previous due
                val receivableAmount = transaction.due.plus(transaction.customerOrSupplierPreviousAmount)

                // update customer last invoice date and receivable amount
                DatabaseManager.update(receivableAmount, DatabaseNode.CUSTOMER, transaction.customerOrSupplierId!!, DatabaseNode.CUSTOMER_RECEIVABLE_AMOUNT)
            }

            DatabaseManager.update(MyUtils.currentDateFormatted, DatabaseNode.CUSTOMER, transaction.customerOrSupplierId!!, DatabaseNode.LAST_INVOICE)

        } else {

            transaction.products.forEach {

                // calculate new stock quantity after buy some product
                val newStockQuantity = it.product!!.availableStock.plus(it.quantity)

                // update the existing product
                val product = it.product
                product?.availableStock = newStockQuantity
                product?.lastSupply = MyUtils.currentDateFormatted

                // update current product new stock quantity
                DatabaseManager.update(product, DatabaseNode.PRODUCT, it.product?.brand!!, it.productId!!)

                if (transaction.due > 0) {
                    // add current due to previous payable amount
                    val payableAmount = transaction.due.plus(transaction.customerOrSupplierPreviousAmount)

                    // update customer last invoice date and receivable amount
                    DatabaseManager.update(payableAmount, DatabaseNode.SUPPLIER, transaction.customerOrSupplierId!!, DatabaseNode.SUPPLIER_PAYABLE_AMOUNT)
                }

                DatabaseManager.update(MyUtils.currentDateFormatted, DatabaseNode.SUPPLIER, transaction.customerOrSupplierId!!, DatabaseNode.LAST_INVOICE)
            }
        }

        mTransactionView.onTransactionComplete(true)
    }
}