package com.amirsons.inventory.event

import com.amirsons.inventory.datamanager.model.Customer
import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.datamanager.model.ProductCart
import com.amirsons.inventory.datamanager.model.Supplier
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerClickListener

interface OnCustomerItemClickedListener : BaseRecyclerClickListener<Customer> {
}

interface OnCartProductListener : BaseRecyclerClickListener<ProductCart>{
    fun onCartProductRemove(position: Int, cartProduct: ProductCart)
}

interface OnProductItemClickedListener : BaseRecyclerClickListener<Product>

interface OnSupplierItemClickedListener : BaseRecyclerClickListener<Supplier> {
}