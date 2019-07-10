package com.amirsons.inventory.datamanager.firebase

import com.google.firebase.database.*

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
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        rootDatabaseRef = FirebaseDatabase.getInstance().reference
    }

    /**
     * create empty node inside given reference [databaseReference] and pass the unique id
     */
    private fun createNewId(databaseReference: DatabaseReference) : String? {
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
}

/**
 * enum list of database nodes
 */
object DatabaseNode{
    const val PRODUCT = "products"
    const val CUSTOMER = "customers"
    const val SUPPLIER = "suppliers"
}