package com.amirsons.inventory.ui.activity.transection

import android.os.Bundle
import android.view.ViewGroup
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.ProductCart
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.recyclerview.viewholder.SelectedCartProductHolder
import com.amirsons.inventory.ui.base.BaseActivity
import com.amirsons.inventory.ui.widgets.BottomSheetAvailableProduct
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : BaseActivity(), TransactionActivityView {

    private lateinit var mCartListAdapter: RecyclerViewAdapter<ProductCart, BaseRecyclerClickListener<ProductCart>>
    private lateinit var mTransactionPresenter: TransactionActivityPresenter

    private lateinit var transaction: Transaction

    override val contentLayout: Int
        get() = R.layout.activity_transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create new transaction object
        transaction = Transaction()

        // init transaction mvp
        mTransactionPresenter = TransactionActivityMvp(this)

        // init selected cart product recycler view adapter
        initCartListRecyclerView()

        // btn add product click listener
        btn_add_product.setOnClickListener{
            showAvailableProductDialog()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        finish()
    }

    /**
     * show bottom sheet available product
     */
    private fun showAvailableProductDialog() {
        val bottomSheetAvailableProduct = BottomSheetAvailableProduct(this, this)
        bottomSheetAvailableProduct.show()
    }

    /**
     * add selected cart product to list
     * this product come from bottom sheet
     */
    override fun addProductToCartList(productCart: ProductCart) {

        if (tv_item_selected_count.visibility == ViewGroup.GONE) {

            tv_item_selected_count.visibility = ViewGroup.VISIBLE
            rv_product_cart_list.visibility = ViewGroup.VISIBLE
            tv_no_item.visibility = ViewGroup.GONE
        }

        mCartListAdapter.add(productCart)

        val countText = "${mCartListAdapter.itemCount} Product Selected"
        tv_item_selected_count.text = countText
    }


    /**
     * init selected cart list recycler view
     */
    private fun initCartListRecyclerView() {

        mCartListAdapter = object : RecyclerViewAdapter<ProductCart, BaseRecyclerClickListener<ProductCart>>(transaction.products) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<ProductCart, BaseRecyclerClickListener<ProductCart>> {
                return SelectedCartProductHolder(inflate(R.layout.item_transaction_cart_product, parent))
            }
        }

        rv_product_cart_list.adapter = mCartListAdapter
    }
}
