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

    override fun onLoad() {

        RetrofitClient.instance.dataSet.enqueue(object : Callback<ArrayList<Transaction>> {

            override fun onResponse(call: Call<ArrayList<Transaction>>, response: Response<ArrayList<Transaction>>) {

                if (response.body() != null)
                    mTransactionView.setList(response.body() ?: ArrayList())

                Log.wtf(TAG, "onResponse: ")
            }

            override fun onFailure(call: Call<ArrayList<Transaction>>, t: Throwable) {
                mTransactionView.showToast(t.message ?: "")
                Log.wtf(TAG, t.message)
            }
        })
    }

    companion object {
        private val TAG = "TransactionMvp"
    }
}
