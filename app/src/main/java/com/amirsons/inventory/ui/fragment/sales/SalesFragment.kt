package com.amirsons.inventory.ui.fragment.sales


import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.TransactionHistory
import com.amirsons.inventory.ui.base.BaseFragment
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.ui.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.ui.recyclerview.viewholder.TransactionHistoryHolder
import kotlinx.android.synthetic.main.fragment_sales.*

/**
 * A simple [BaseFragment] subclass.
 */
class SalesFragment : BaseFragment(), SalesView, BaseRecyclerClickListener<TransactionHistory> {

    private lateinit var mSalesListAdapter: RecyclerViewAdapter<TransactionHistory, BaseRecyclerClickListener<TransactionHistory>>
    private lateinit var mSalesPresenter: SalesPresenter

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
        mSalesPresenter = SalesMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set toolbar name
        setToolbar(view, context.getString(R.string.title_sales_toolbar), false)

        initRecyclerView()

        mSalesPresenter.onLoadList()
    }

    private fun initRecyclerView() {

        mSalesListAdapter = object : RecyclerViewAdapter<TransactionHistory, BaseRecyclerClickListener<TransactionHistory>>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<TransactionHistory, BaseRecyclerClickListener<TransactionHistory>> {
                return TransactionHistoryHolder(inflate(R.layout.item_transaction_history, parent))
            }
        }

        mSalesListAdapter.setListener(this)
        rv_sales_list.adapter = mSalesListAdapter
    }

    override fun setListToView(transactionHistoryList: ArrayList<TransactionHistory>) {
        mSalesListAdapter.setItems(transactionHistoryList)
    }

    override fun onItemClickListener(item: TransactionHistory, position: Int) {

    }

    override fun onPause() {
        super.onPause()
        Log.wtf(TAG, "sales fragment pause")
        mSalesPresenter.onRemoveDatabaseListener()
    }
}
