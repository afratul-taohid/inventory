package com.amirsons.inventory.ui.widgets

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.ProductCart
import com.amirsons.inventory.ui.activity.transaction.TransactionActivityView
import com.amirsons.inventory.utils.KeyboardUtils
import kotlinx.android.synthetic.main.content_transaction_selected_product_cart.*

/**
 * Created by Taohid on 12, July, 2019
 * Email: taohid32@gmail.com
 */
class BottomSheetSelectedCartProduct(context: Context, private val selectedProduct: ProductCart, private val listener: TransactionActivityView): MyBottomSheetDialog(context){

    override val getContentLayout: Int
        get() = R.layout.content_transaction_selected_product_cart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove the back button, cause its not needed for this bottom sheet
        iv_back.visibility = View.GONE
        tv_header_title.visibility = View.GONE

        val size = "(${selectedProduct.product?.size})"
        val availableCount = "${selectedProduct.product?.availableStock} unit"

        tv_cart_item_category.text = selectedProduct.product?.category
        tv_cart_item_size.text = size
        tv_cart_item_brand_name.text = selectedProduct.product?.brand
        tv_cart_product_weight.text = selectedProduct.product?.weight
        tv_cart_item_available_unit.text = availableCount

        // check if update retails price is required or not
        cb_update_retail.isChecked = selectedProduct.isUpdatePrice

        // set per unit price text
        et_per_unit_price.setText(selectedProduct.unitPrice.toString())

        // set selected unit
        et_unit.setText(selectedProduct.quantity.toString())

        // set updated price to total price
        val totalPrice = "${context.getString(R.string.bdt)} ${(selectedProduct.unitPrice * selectedProduct.quantity)}"
        tv_total.text = totalPrice

        // add unit text watcher
        et_unit.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

                val unitText = s.toString()

                if (unitText.isNotEmpty() && (unitText.toInt() == 0 || unitText.toInt() > selectedProduct.product!!.availableStock)){
                    et_unit.text.clear()
                    return
                }

                val priceText = et_per_unit_price.text.toString()

                // calculate total price
                calculateTotalPrice(s.toString(), priceText)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        // add price text watcher
        et_per_unit_price.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

                val unitText = et_unit.text.toString()

                // calculate total price
                calculateTotalPrice(unitText, s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        // change button text to update cart
        btn_add_cart.text = context.getString(R.string.update_cart_text)
        btn_add_cart.setOnClickListener {

            // get the price and unit text
            val parUnitPrice = et_per_unit_price.text.toString()
            val unit = et_unit.text.toString()

            if(parUnitPrice.isEmpty()){
                et_per_unit_price.error = "required"
                return@setOnClickListener
            }

            if (unit.isEmpty()){
                et_unit.error = "required"
                return@setOnClickListener
            }

            selectedProduct.isUpdatePrice = cb_update_retail.isChecked
            selectedProduct.quantity = unit.toInt()
            selectedProduct.unitPrice = parUnitPrice.toInt()

            // pass the selected product cart list
            listener.addProductToCartList(selectedProduct)

            // hide the soft keyboard
            KeyboardUtils.hideSoftInput(et_per_unit_price)

            // dismiss this bottomsheet dialog
            dismiss()
        }
    }

    /**
     * calculate total price when unit or price change
     */
    private fun calculateTotalPrice(unitText: String, priceText: String) {

        // update price
        val price = if (priceText.isEmpty()) 0 else priceText.toInt()
        val unit = if (unitText.isEmpty()) 0 else unitText.toInt()

        // set updated price to total price
        val totalPrice = "${context.getString(R.string.bdt)} ${(price * unit)}"
        tv_total.text = totalPrice
    }
}