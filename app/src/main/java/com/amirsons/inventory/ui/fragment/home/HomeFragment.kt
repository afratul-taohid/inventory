package com.amirsons.inventory.ui.fragment.home


import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.TodayTransactionSummery
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.ui.base.BaseFragment
import com.amirsons.inventory.utils.stringspan.SpanSection
import com.amirsons.inventory.utils.stringspan.StringSpanBuilder
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

/**
 * A simple [BaseFragment] subclass.
 */
class HomeFragment : BaseFragment(), HomeView {

    private lateinit var mHomePresenter: HomePresenter

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

        mHomePresenter.loadCurrentDaySummery()

//        // total sale view
//        val saleSpanBuilder = StringSpanBuilder.instance
//
//        val spanSection = SpanSection.getInstance("Total sale")
//                .setTypeface(Typeface.BOLD)
//                .setTextSize(SpanSection.TEXT_SIZE_NORMAL)
//                .setTextColor(Color.parseColor("#B4B4B4"))
//        saleSpanBuilder.append(spanSection)
//
//        saleSpanBuilder.appendNewLine()
//        saleSpanBuilder.appendNewLine()
//
//        val tkSymbolSection = SpanSection.getInstance("৳ ")
//                .setTextSize(SpanSection.TEXT_SIZE_MEDIUM)
//        saleSpanBuilder.append(tkSymbolSection)
//
//        val amountSection = SpanSection.getInstance("5000")
//                .setTextColor(Color.parseColor("#3CD064"))
//                .setTextSize(SpanSection.TEXT_SIZE_LARGE)
//        saleSpanBuilder.append(amountSection)
//        saleSpanBuilder.buildWithTextView(tv_total_sale)
//
//        // total supply view
//        val supplySpanBuilder = StringSpanBuilder.instance
//        spanSection.setText("Total supply")
//        supplySpanBuilder.append(spanSection)
//
//        supplySpanBuilder.appendNewLine()
//        supplySpanBuilder.appendNewLine()
//
//        supplySpanBuilder.append(tkSymbolSection)
//
//        amountSection.setText("10000")
//        amountSection.setTextColor(Color.parseColor("#17DBFF"))
//        supplySpanBuilder.append(amountSection)
//        supplySpanBuilder.buildWithTextView(tv_total_supply)
//
//        // paid view
//        val paidSpanBuilder = StringSpanBuilder.instance
//        spanSection.setText("Paid")
//        paidSpanBuilder.append(spanSection)
//
//        paidSpanBuilder.appendNewLine()
//        paidSpanBuilder.appendNewLine()
//
//        paidSpanBuilder.append(tkSymbolSection)
//
//        amountSection.setTextColor(Color.parseColor("#FFC069"))
//        amountSection.setText("3500")
//        paidSpanBuilder.append(amountSection)
//        paidSpanBuilder.buildWithTextView(tv_paid)
//
//        // payable view
//        val payableSpanBuilder = StringSpanBuilder.instance
//        spanSection.setText("Payable")
//        payableSpanBuilder.append(spanSection)
//
//        payableSpanBuilder.appendNewLine()
//        payableSpanBuilder.appendNewLine()
//
//        payableSpanBuilder.append(tkSymbolSection)
//
//        amountSection.setText("3000")
//        amountSection.setTextColor(Color.parseColor("#FFC069"))
//        payableSpanBuilder.append(amountSection)
//        payableSpanBuilder.buildWithTextView(tv_payable)

//        fab_edit.setOnClickListener {
//            IntentUtils.instance.onActivityIntentWithoutExtras(context, TransactionActivity::class.java)
//        }
    }


    override fun setList(dataList: ArrayList<Transaction>) {

    }

    override fun onLoadCurrentDaySummery(todayTransactionSummery: TodayTransactionSummery) {

        // total sale view
        val saleSpanBuilder = StringSpanBuilder.instance

        val spanSection = SpanSection.getInstance("Total sale")
                .setTypeface(Typeface.BOLD)
                .setTextSize(SpanSection.TEXT_SIZE_NORMAL)
        saleSpanBuilder.append(spanSection)

        saleSpanBuilder.appendNewLine()
        saleSpanBuilder.appendNewLine()

        val tkSymbolSection = SpanSection.getInstance("৳ ")
                .setTextSize(SpanSection.TEXT_SIZE_MEDIUM)
        saleSpanBuilder.append(tkSymbolSection)

        val amountSection = SpanSection.getInstance(todayTransactionSummery.totalSell.toString())
                .setTextColor(context.resources.getColor(R.color.total_sale_color))
                .setTextSize(SpanSection.TEXT_SIZE_LARGE)
        saleSpanBuilder.append(amountSection)
        saleSpanBuilder.buildWithTextView(tv_total_sale)
    }

    override fun onPause() {
        super.onPause()
        mHomePresenter.onRemoveDatabaseListener()
    }
}
