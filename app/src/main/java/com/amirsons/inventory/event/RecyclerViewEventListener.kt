package com.amirsons.inventory.event

import com.amirsons.inventory.datamanager.model.Customer
import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.datamanager.model.Supplier
import com.amirsons.inventory.recyclerview.base.BaseRecyclerClickListener

interface OnCustomerItemClickedListener : BaseRecyclerClickListener<Customer> {
    fun onSingleViewClicked(customer: Customer, position: Int)
}

interface OnProductItemClickedListener : BaseRecyclerClickListener<Product>

interface OnSupplierItemClickedListener : BaseRecyclerClickListener<Supplier> {
    fun onSingleViewClicked(supplier: Supplier, position: Int)
}