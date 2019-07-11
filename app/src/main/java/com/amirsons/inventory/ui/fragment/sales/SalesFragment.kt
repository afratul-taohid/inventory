package com.amirsons.inventory.ui.fragment.sales


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.amirsons.inventory.R
import com.amirsons.inventory.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.event.OnProductItemClickedListener
import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.ui.base.BaseFragment
import com.amirsons.inventory.recyclerview.viewholder.ProductHolder
import kotlinx.android.synthetic.main.fragment_sales.*
import java.util.*

/**
 * A simple [BaseFragment] subclass.
 */
class SalesFragment : BaseFragment(), SalesView, OnProductItemClickedListener {

    override fun onItemClickListener(item: Product, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var mProductPresenter: SalesPresenter

    override val contentLayout: Int
        get() = R.layout.fragment_sales

    companion object {

        val instance: SalesFragment
            get() {
                val fragment = SalesFragment()
                val args = Bundle()
                fragment.arguments = args
                return fragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProductPresenter = SalesMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(getContext())
        rv_sales_list.layoutManager = layoutManager

        mProductPresenter.onLoadList()
    }

    override fun setListToView(productList: ArrayList<Product>) {

        val mProductAdapter = object : RecyclerViewAdapter<Product, OnProductItemClickedListener>(productList) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Product, OnProductItemClickedListener> {
                return ProductHolder(inflate(R.layout.item_inventory, parent))
            }
        }

        mProductAdapter.setListener(this)
        rv_sales_list.adapter = mProductAdapter
    }

}
