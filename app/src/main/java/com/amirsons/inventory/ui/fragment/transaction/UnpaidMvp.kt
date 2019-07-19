package com.amirsons.inventory.ui.fragment.transaction

import android.util.Log
import com.amirsons.inventory.api.RetrofitClient
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal interface UnpaidView : BaseView {
    fun setList(dataList: ArrayList<Transaction>)
    fun showToast(message: String)
}

internal interface UnpaidPresenter : BasePresenter {
    fun onLoad()
}

class UnpaidMvp internal constructor(private val mTransactionView: UnpaidView) : UnpaidPresenter {

    override fun onRemoveDatabaseListener() {


    }

    override fun onLoad() {
    }

    companion object {
        private val TAG = "TransactionMvp"
    }
}
