package com.amirsons.inventory.ui.fragment.supplies


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
import kotlinx.android.synthetic.main.fragment_supplies.*

/**
 * A simple [BaseFragment] subclass.
 */
class SuppliesFragment : BaseFragment(), SuppliesView, BaseRecyclerClickListener<TransactionHistory> {

    private lateinit var mSuppliesListAdapter: RecyclerViewAdapter<TransactionHistory, BaseRecyclerClickListener<TransactionHistory>>
    private lateinit var mSuppliesPresenter: SuppliesPresenter

    override val contentLayout: Int
        get() = R.layout.fragment_supplies

    companion object {

        val instance: SuppliesFragment
            get() {
                val fragment = SuppliesFragment()
                val args = Bundle()
                fragment.arguments = args
                return fragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSuppliesPresenter = SuppliesMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set toolbar name
        setToolbar(view, context.getString(R.string.title_supplies_toolbar), false)

        initRecyclerView()

        mSuppliesPresenter.onLoadList()
    }

    private fun initRecyclerView() {

        mSuppliesListAdapter = object : RecyclerViewAdapter<TransactionHistory, BaseRecyclerClickListener<TransactionHistory>>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<TransactionHistory, BaseRecyclerClickListener<TransactionHistory>> {
                return TransactionHistoryHolder(inflate(R.layout.item_transaction_history, parent))
            }
        }

        mSuppliesListAdapter.setListener(this)
        rv_supplies_list.adapter = mSuppliesListAdapter
    }

    override fun setListToView(transactionHistoryList: ArrayList<TransactionHistory>) {
        mSuppliesListAdapter.setItems(transactionHistoryList)
        Log.wtf(TAG, "${transactionHistoryList.size}")
    }

    override fun onItemClickListener(item: TransactionHistory, position: Int) {

    }

    override fun onPause() {
        super.onPause()
        mSuppliesPresenter.onRemoveDatabaseListener()
    }
}
