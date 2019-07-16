package com.amirsons.inventory.ui.widgets

import android.content.Context
import android.os.Bundle
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.Customer
import kotlinx.android.synthetic.main.bottomsheet_add_customer.*

/**
 * Created by Taohid on 08, July, 2019
 * Email: taohid32@gmail.com
 */
class BottomSheetAddCustomer(context: Context, private val title: String? = null, private val listener: OnAddCustomerConfirm? = null) : MyBottomSheetDialog(context) {

    override val getContentLayout: Int
        get() = R.layout.bottomsheet_add_customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv_title.text = title

        btn_done.setOnClickListener {

            if (isValidate()) {

                val customer = Customer()

                customer.name = et_customer_name.text.toString()
                customer.mobile = et_mobile.text.toString()
                customer.address = et_address.text.toString()

                if (listener?.onConfirm(customer) == true){

                    dismiss()
                }
            }
        }
    }

    private fun isValidate() : Boolean {

        var isValidate = true

        if (et_customer_name.text.toString().isEmpty()){
            isValidate = false
            et_customer_name.error = "Required !!"
        }

        if (et_mobile.text.toString().isEmpty()){
            isValidate = false
            et_mobile.error = "Required !!"
        }

        return isValidate
    }
}

interface OnAddCustomerConfirm {
    fun onConfirm(item: Customer): Boolean
}