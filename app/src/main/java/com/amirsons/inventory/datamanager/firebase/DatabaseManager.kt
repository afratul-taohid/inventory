package com.amirsons.inventory.datamanager.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by Taohid on 07, July, 2019
 * Email: taohid32@gmail.com
 */

object DatabaseManager {

    private var rootDatabaseRef: DatabaseReference

    /**
     * initialize the firebase database
     */
    init {

        val uid = FirebaseAuth.getInstance().currentUser?.uid

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        rootDatabaseRef = FirebaseDatabase.getInstance().reference.child(uid!!)
        rootDatabaseRef.keepSynced(true)
    }

    /**
     * create empty node inside given reference [databaseReference] and pass the unique id
     */
    fun createNewId(databaseReference: DatabaseReference) : String? {
        return databaseReference.push().key
    }

    /**
     * get database reference of given child [nodes]
     */
    fun getDatabaseRef(vararg nodes : String) : DatabaseReference {

        var ref = rootDatabaseRef

        nodes.forEach {
            ref = ref.child(it)
        }

        return ref
    }


    /**
     * [data] which will be inserted into database
     * [databaseNodes] list of database child nodes from root nodes pass via params
     */
    fun <T> add(data: T, vararg databaseNodes: String) {

        // get the ref from root
        val ref = getDatabaseRef(*databaseNodes)

        // create new node in current ref and get the id
        val id = createNewId(ref)

        // assume id is not null and set the value of current ref
        id?.let { ref.child(id).setValue(data) }
    }

    /**
     * [data] which will be inserted into database
     * [databaseNodes] list of database child nodes from root nodes pass via params
     */
    fun <T> update(data: T, vararg databaseNodes: String) {

        // get the update product node ref from root
        val ref = getDatabaseRef(*databaseNodes)

        // update the current ref value
        ref.setValue(data)
    }
}

/**
 * enum list of database nodes
 */
object DatabaseNode{
    const val PRODUCT = "products"
    const val CUSTOMER = "customers"
    const val CUSTOMER_RECEIVABLE_AMOUNT = "receivableAmount"
    const val LAST_INVOICE = "lastInvoiceDate"
    const val SUPPLIER = "suppliers"
    const val SUPPLIER_PAYABLE_AMOUNT = "payableAmount"
    const val TRANSACTION = "transactions"
    const val TRANSACTION_TYPE = "transactionType"
    const val TRANSACTION_DATE = "date"
}