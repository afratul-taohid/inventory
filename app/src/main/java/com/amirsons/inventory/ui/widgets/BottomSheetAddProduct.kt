package com.amirsons.inventory.ui.widgets

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.utils.KeyboardUtils
import com.amirsons.inventory.utils.MyUtils
import kotlinx.android.synthetic.main.bottomsheet_add_product.*


/**
 * Created by Taohid on 08, July, 2019
 * Email: taohid32@gmail.com
 */
class BottomSheetAddProduct(context: Context, private val brandNameList: ArrayList<String>, private val listener: OnAddProductConfirm? = null) : MyBottomSheetDialog(context) {

    override val getContentLayout: Int
        get() = R.layout.bottomsheet_add_product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, brandNameList)
        ac_brand_name.setAdapter(arrayAdapter)
        ac_brand_name.threshold = 0

        btn_add_product.setOnClickListener {

            if (isValidate()) {

                val product = Product()

                product.brand = ac_brand_name.text.toString()
                product.size = "${et_product_first_size.text} x ${et_product_last_size.text}"
                product.weight = if (et_product_weight.text.isNotEmpty()) "${et_product_weight.text} gsm" else ""
                product.retailPrice = if (et_retail_price.text.toString().isNotEmpty()) et_retail_price.text.toString().toInt() else 0
                product.category = product_category_spinner.selectedItem as String
                product.lastSupply = MyUtils.currentDateFormatted

                if (et_stock_quantity.text.toString().isNotEmpty()){
                    product.availableStock =  et_stock_quantity.text.toString().toInt()
                }

                if (listener?.onConfirm(product) == true) {

                    // hide the soft keyboard
                    KeyboardUtils.hideSoftInput(ac_brand_name)

                    dismiss()
                }
            }
        }
    }

    private fun isValidate() : Boolean {

        var isValidate = true

        if (ac_brand_name.text.toString().isEmpty()){
            isValidate = false
            ac_brand_name.error = "Required !!"
        }

        return isValidate
    }
}

interface OnAddProductConfirm{
    fun onConfirm(product: Product): Boolean
}