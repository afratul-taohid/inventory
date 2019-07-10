package com.amirsons.inventory.ui.fragment.home

import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView

/**
 * Created by Taohid on 06, July, 2019
 * Email: taohid32@gmail.com
 */

internal interface HomeView : BaseView {
    fun setList(dataList: ArrayList<Transaction>)
}

internal interface HomePresenter : BasePresenter {
    fun onLoad()
}

class HomeMvp internal constructor(private val mMainView: HomeView) : HomePresenter {

    override fun onLoad() {

    }
}