package com.amirsons.inventory.ui.fragment.transaction


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amirsons.inventory.R
import com.amirsons.inventory.ui.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.ui.activity.transaction.TransactionActivity
import com.amirsons.inventory.ui.base.BaseFragment
import com.amirsons.inventory.utils.IntentUtils
import com.amirsons.inventory.ui.recyclerview.viewholder.UnpaidHolder
import kotlinx.android.synthetic.main.fragment_unpaid.*
import java.util.*

/**
 * A simple [BaseFragment] subclass.
 */
class UnpaidFragment : BaseFragment(), UnpaidView, View.OnClickListener {

    private lateinit var mTransactionPresenter: UnpaidPresenter

    override val contentLayout: Int
        get() = R.layout.fragment_unpaid

    companion object {
        val instance: UnpaidFragment
            get() {
                val fragment = UnpaidFragment()
                val args = Bundle()
                fragment.arguments = args
                return fragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTransactionPresenter = UnpaidMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(mTransactionPresenter.context)
        rv_unpaid_view.layoutManager = layoutManager
        
        mTransactionPresenter.onLoad()

        btn_fab.setOnClickListener(this)
    }

    override fun setList(dataList: ArrayList<Transaction>) {

        val unpaidAdapter = object : RecyclerViewAdapter<Transaction, BaseRecyclerClickListener<Transaction>>(dataList) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Transaction, BaseRecyclerClickListener<Transaction>> {
                return UnpaidHolder(inflate(R.layout.item_transaction_history, parent))
            }
        }

        unpaidAdapter.setListener(object : BaseRecyclerClickListener<Transaction>{

            override fun onItemClickListener(item: Transaction, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        rv_unpaid_view.adapter = unpaidAdapter
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_fab -> IntentUtils.instance
                    .onActivityIntentWithoutExtras(context, TransactionActivity::class.java)
        }
    }
}
