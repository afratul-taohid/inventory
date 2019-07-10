package com.amirsons.inventory.ui.fragment.supplier

import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.Supplier
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

internal interface SupplierView : BaseView {
    fun setSupplierToView(supplierList: ArrayList<Supplier>)
}

internal interface SupplierPresenter : BasePresenter {
    fun onLoadSupplierList()
    fun onAddSupplierClick(supplier: Supplier)
}

class SupplierMvp internal constructor(private val mSupplierView: SupplierView) : SupplierPresenter {

    override fun onAddSupplierClick(supplier: Supplier) {

        // add new customer into database
        DatabaseManager.add(supplier, DatabaseNode.SUPPLIER)
    }

    override fun onLoadSupplierList() {

        DatabaseManager.getDatabaseRef(DatabaseNode.SUPPLIER).addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val supplierList = ArrayList<Supplier>()

                dataSnapshot.children.forEach {
                    val supplier = it.getValue(Supplier::class.java)
                    supplier?.let { supplierList.add(supplier) }
                }

                if (supplierList.isNotEmpty()) {

                    mSupplierView.setSupplierToView(supplierList)
                }
            }
        })
    }

}
