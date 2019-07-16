package com.amirsons.inventory.ui.fragment.inventory

import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.BrandProduct
import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

internal interface InventoryView : BaseView {
    fun setBrandListToView(brandList: ArrayList<BrandProduct>)
    fun showAddProductBottomSheet(brandNameList: ArrayList<String>)
}

internal interface InventoryPresenter : BasePresenter {
    fun onLoadProductList()
    fun onRemoveDatabaseListener()
    fun onAddProduct(product: Product)
    fun onAddProductClick()
}

class InventoryMvp internal constructor(private val mInventoryView: InventoryView) : InventoryPresenter {

    private val brandNameList = ArrayList<String>()

    private val productLoadListener: ValueEventListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {

            // clear previous brand name list
            brandNameList.clear()

            val brandList : ArrayList<BrandProduct> = ArrayList()

            // loop each brand name node then get product inside brand name node
            dataSnapshot.children.forEach { brandSnapshot ->

                // get the brand name, brand name is the key of product
                val brand = brandSnapshot.key

                // temp list for each branded products
                val products : ArrayList<Product> = ArrayList()

                // loop through brand name for get products
                brandSnapshot?.children?.forEach { productSnapshot ->

                    // get single product object
                    val product = productSnapshot.getValue(Product::class.java)

                    // set the product name and id into model
                    product?.brand = brand
                    product?.productId = productSnapshot.key

                    // assume product not null
                    product?.let {
                        products.add(0, product)
                    }
                }

                brand?.let {

                    // store brand name list to show suggestion on product add
                    brandNameList.add(brand)

                    // finally add brand product with brand name
                    brandList.add(BrandProduct(brand, products))
                }
            }

            mInventoryView.setBrandListToView(brandList)
        }
    }

    override fun onAddProductClick() {
        mInventoryView.showAddProductBottomSheet(brandNameList)
    }

    override fun onAddProduct(product: Product) {
        // add the product into database
        DatabaseManager.add(product, DatabaseNode.PRODUCT, product.brand!!)
    }

    override fun onLoadProductList() {

        // add an event for add product listener
        DatabaseManager.getDatabaseRef(DatabaseNode.PRODUCT).addValueEventListener(productLoadListener)
    }

    override fun onRemoveDatabaseListener() {

        // remove this listener from context after activity / fragment detach
        DatabaseManager.getDatabaseRef(DatabaseNode.PRODUCT).removeEventListener(productLoadListener)
    }
}
