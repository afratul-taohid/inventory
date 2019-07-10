package com.amirsons.inventory.ui.activity.add

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.amirsons.inventory.R
import com.amirsons.inventory.ui.base.BaseActivity
import com.amirsons.inventory.ui.widgets.BottomSheetAvailableProduct
import com.amirsons.inventory.ui.widgets.MyBottomSheetDialog
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.bottomsheet_available_product.*

class TransactionActivity : BaseActivity(), TransactionActivityView, View.OnClickListener {

    private lateinit var mTransactionPresenter: TransactionActivityPresenter

    override val contentLayout: Int
        get() = R.layout.activity_transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mTransactionPresenter = TransactionActivityMvp(this)

        btn_add_product.setOnClickListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_add_product -> showAvailableProductDialog()
        }
    }

    override fun onBackPressed() {
        finish()
    }

    private fun showAvailableProductDialog() {
        val bottomSheetAvailableProduct = BottomSheetAvailableProduct(this)
        bottomSheetAvailableProduct.show()
    }

//    override fun setAvailableList(availableList: ArrayList<Product>) {
//
//        val mAvailableAdapter = object : RecyclerViewAdapter<Product, BaseRecyclerClickListener<Product>>(availableList) {
//            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Product, BaseRecyclerClickListener<Product>> {
//                return AvailableProductHolder(inflate(R.layout.item_available_product, parent))
//            }
//        }
//
//        bottomSheetDialog.rv_available_product_list.adapter = mAvailableAdapter
//    }
}
