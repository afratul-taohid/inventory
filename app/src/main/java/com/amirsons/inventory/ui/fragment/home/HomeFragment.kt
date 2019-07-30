package com.amirsons.inventory.ui.fragment.home


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.RecentSaleProduct
import com.amirsons.inventory.datamanager.model.TransactionSummery
import com.amirsons.inventory.ui.activity.transaction.TransactionActivity
import com.amirsons.inventory.ui.base.BaseFragment
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.ui.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.ui.recyclerview.viewholder.RecentSaleProductHolder
import com.amirsons.inventory.utils.stringspan.SpanSection
import com.amirsons.inventory.utils.stringspan.SpanSection.Companion.TEXT_SIZE_MEDIUM
import com.amirsons.inventory.utils.stringspan.StringSpanBuilder
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


/**
 * A simple [BaseFragment] subclass.
 */
class HomeFragment : BaseFragment(), HomeView {

    private lateinit var recentSaleProductAdapter: RecyclerViewAdapter<RecentSaleProduct, BaseRecyclerClickListener<RecentSaleProduct>>
    private lateinit var mHomePresenter: HomePresenter
    private lateinit var selectedTextView: TextView

    override val contentLayout: Int
        get() = R.layout.fragment_home

    companion object {
        val instance: HomeFragment
            get() {
                val fragment = HomeFragment()
                val args = Bundle()
                fragment.arguments = args
                return fragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomePresenter = HomeMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(view, "Dashboard", false)

        initRecentSaleProductView()

        mHomePresenter.loadCurrentDaySummery()

        selectedTextView = tv_last_week
        mHomePresenter.loadCurrentWeekSummery()

        tv_last_week.setOnClickListener {
            selectedTextView = tv_last_week
            mHomePresenter.loadCurrentWeekSummery()
        }

        tv_last_month.setOnClickListener {
            // get previous 30 days summery data
            selectedTextView = tv_last_month
            mHomePresenter.loadCurrentMonthSummery()
        }

        btn_create_transaction.setOnClickListener {
            startActivity(Intent(context, TransactionActivity::class.java))
        }
    }

    private fun initRecentSaleProductView() {

        recentSaleProductAdapter = object : RecyclerViewAdapter<RecentSaleProduct, BaseRecyclerClickListener<RecentSaleProduct>>(){

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<RecentSaleProduct, BaseRecyclerClickListener<RecentSaleProduct>> {
                return RecentSaleProductHolder(inflate(R.layout.item_recent_sale_item, parent))
            }
        }

        rv_product_list.adapter = recentSaleProductAdapter
    }

    override fun onLoadRecentSaleProductList(dataList: ArrayList<RecentSaleProduct>) {
        recentSaleProductAdapter.setItems(dataList)
    }

    override fun onLoadCurrentDaySummery(todayTransactionSummery: TransactionSummery) {

        println("coroutine: I'm working in thread ${Thread.currentThread().name}")

        // total sale view
        val saleSpanBuilder = StringSpanBuilder.instance

        val spanSection = SpanSection.getInstance("Today sale")
                .setTypeface(Typeface.BOLD)
                .setTextSize(SpanSection.TEXT_SIZE_NORMAL)
        saleSpanBuilder.append(spanSection)

        saleSpanBuilder.appendNewLine()
        saleSpanBuilder.appendNewLine()

        val tkSymbolSection = SpanSection.getInstance("৳ ")
                .setTextSize(TEXT_SIZE_MEDIUM)
        saleSpanBuilder.append(tkSymbolSection)

        val amountSection = SpanSection.getInstance(todayTransactionSummery.totalSell.toString())
                .setTextColor(ContextCompat.getColor(context, R.color.total_sale_color))
                .setTextSize(SpanSection.TEXT_SIZE_LARGE)
        saleSpanBuilder.append(amountSection)
        saleSpanBuilder.buildWithTextView(tv_total_sale)

        // total supply view
        val supplySpanBuilder = StringSpanBuilder.instance
        spanSection.setText("Today supply")
        supplySpanBuilder.append(spanSection)

        supplySpanBuilder.appendNewLine()
        supplySpanBuilder.appendNewLine()

        supplySpanBuilder.append(tkSymbolSection)

        amountSection.setText(todayTransactionSummery.totalSupply.toString())
        amountSection.setTextColor(ContextCompat.getColor(context, R.color.total_supply_color))
        supplySpanBuilder.append(amountSection)
        supplySpanBuilder.buildWithTextView(tv_total_supply)

        // paid view
        val paidSpanBuilder = StringSpanBuilder.instance
        spanSection.setText("Today Due")
        paidSpanBuilder.append(spanSection)

        paidSpanBuilder.appendNewLine()
        paidSpanBuilder.appendNewLine()

        paidSpanBuilder.append(tkSymbolSection)

        amountSection.setText(todayTransactionSummery.totalDue.toString())
        amountSection.setTextColor(ContextCompat.getColor(context, R.color.total_due_color))
        paidSpanBuilder.append(amountSection)
        paidSpanBuilder.buildWithTextView(tv_paid)

        // payable view
        val payableSpanBuilder = StringSpanBuilder.instance
        spanSection.setText("Today Payable")
        payableSpanBuilder.append(spanSection)

        payableSpanBuilder.appendNewLine()
        payableSpanBuilder.appendNewLine()

        payableSpanBuilder.append(tkSymbolSection)

        amountSection.setText(todayTransactionSummery.totalPayable.toString())
        amountSection.setTextColor(ContextCompat.getColor(context, R.color.total_due_color))
        payableSpanBuilder.append(amountSection)
        payableSpanBuilder.buildWithTextView(tv_payable)
    }

    override fun onLoadCurrentWeekSummery(transactionSummery: TransactionSummery) {
        setSummeryDataToView(transactionSummery)
    }

    override fun onLoadCurrentMonthSummery(transactionSummery: TransactionSummery) {
        setSummeryDataToView(transactionSummery)
    }

    /**
     * set selected summery value
     */
    private fun setSummeryDataToView(transactionSummery: TransactionSummery) {

        if (selectedTextView.id == R.id.tv_last_week){
            tv_last_month.setTextColor(ContextCompat.getColor(context, R.color.gray))
            tv_last_month.setShadowLayer(0f, 0f, 0f, Color.parseColor("#20000000"))
        } else{
            tv_last_week.setTextColor(ContextCompat.getColor(context, R.color.gray))
            tv_last_week.setShadowLayer(0f, 0f, 0f, Color.parseColor("#20000000"))
        }

        selectedTextView.setTextColor(ContextCompat.getColor(context, R.color.selected_textview_color))
        selectedTextView.setShadowLayer(15f, 10f, 10f, Color.parseColor("#20000000"))

        val totalSellValue = "${context.getString(R.string.bdt)} ${transactionSummery.totalSell}"
        val totalDueValue = "${context.getString(R.string.bdt)} ${transactionSummery.totalDue}"
        val totalSupplyValue = "${context.getString(R.string.bdt)} ${transactionSummery.totalSupply}"

        tv_total_sale_value.text = totalSellValue
        tv_total_due_value.text = totalDueValue
        tv_total_supply_value.text = totalSupplyValue
    }

    override fun onPause() {
        super.onPause()
        mHomePresenter.onRemoveDatabaseListener()
    }
}
