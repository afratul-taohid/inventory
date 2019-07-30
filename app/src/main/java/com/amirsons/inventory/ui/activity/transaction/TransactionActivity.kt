
package com.amirsons.inventory.ui.activity.transaction

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.amirsons.inventory.R
import com.amirsons.inventory.app.Constant
import com.amirsons.inventory.datamanager.model.CustomerOrSupplierSearchItem
import com.amirsons.inventory.datamanager.model.ProductCart
import com.amirsons.inventory.datamanager.model.Transaction
import com.amirsons.inventory.event.OnCartProductListener
import com.amirsons.inventory.ui.base.BaseActivity
import com.amirsons.inventory.ui.recyclerview.adapter.CustomerSearchAdapter
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.ui.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.ui.recyclerview.viewholder.SelectedCartProductHolder
import com.amirsons.inventory.ui.widgets.BottomSheetAvailableProduct
import com.amirsons.inventory.ui.widgets.BottomSheetSelectedCartProduct
import com.amirsons.inventory.ui.widgets.MyDialog
import com.amirsons.inventory.ui.widgets.MySnackBar
import com.amirsons.inventory.utils.MyUtils
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.action_bar.view.*
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : BaseActivity(), TransactionActivityView {

    private lateinit var customerSearchAdapter: CustomerSearchAdapter
    private lateinit var mCartListAdapter: RecyclerViewAdapter<ProductCart, OnCartProductListener>
    private lateinit var mTransactionPresenter: TransactionActivityPresenter

    private lateinit var transaction: Transaction

    override val contentLayout: Int
        get() = R.layout.activity_transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // enable back button in toolbar
        enableBackButton(toolbar)

        // set the toolbar title
        toolbar.tv_action_title.text = getString(R.string.title_transaction)

        // init customer search list items view
        initCustomerSearchAdapter()

        // init transaction mvp
        mTransactionPresenter = TransactionActivityMvp(this)

        // create new transaction object
        transaction = Transaction()

        // set initial transaction type to sale
        transaction.transactionType = Constant.TRANSACTION_SELL
        btn_sale.isSelected = true

        // get customer search list based on transaction type
        mTransactionPresenter.loadCustomerList(Constant.TRANSACTION_SELL)

        // init selected cart product recycler view adapter
        initCartListRecyclerView()

        // initialize all button click listener
        initAllEventListener()
    }

    /**
     * initialize the autocomplete textview for show customer suggestion
     */
    private fun initCustomerSearchAdapter() {

        customerSearchAdapter = CustomerSearchAdapter(this, R.layout.item_search_customer, ArrayList())
        ac_tv_customer_name.setAdapter(customerSearchAdapter)
        ac_tv_customer_name.threshold = 1
        ac_tv_customer_name.setOnItemClickListener { _, _, position, _ ->

            // search selected item
            val item = customerSearchAdapter.getItem(position)

            // set customer or supplier name edit text view
            ac_tv_customer_name.setText(item?.name)

            // set customer or supplier id to transaction model
            transaction.customerOrSupplierId = item?.id

            // add customer or supplier previous amount to transaction model for update previousAmount and last invoice date
            transaction.customerOrSupplierPreviousAmount = item?.previousAmount?:0
        }
    }

    /**
     * initialize all button or edit text click or text change listener
     */
    private fun initAllEventListener() {

        // set button sale click
        btn_sale.setOnClickListener {

            if (btn_sale.isSelected) {
                return@setOnClickListener
            }

            btn_sale.isSelected = true
            btn_buy.isSelected = false

            ac_tv_customer_name.hint = "Customer Name"

            transaction.transactionType = Constant.TRANSACTION_SELL

            // get customer search list based on transaction type
            mTransactionPresenter.loadCustomerList(Constant.TRANSACTION_SELL)

            removeAllPreviousSelection()
        }

        // set button buy click
        btn_buy.setOnClickListener {

            if (btn_buy.isSelected){
                return@setOnClickListener
            }

            btn_buy.isSelected = true
            btn_sale.isSelected = false

            ac_tv_customer_name.hint = "Supplier Name"

            transaction.transactionType = Constant.TRANSACTION_BUY

            // get customer search list based on transaction type
            mTransactionPresenter.loadCustomerList(Constant.TRANSACTION_BUY)

            removeAllPreviousSelection()
        }

        et_other_cost_value.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(s: Editable?) {

                val extraCost = s.toString()

                if (extraCost.isEmpty()) {
                    transaction.extraCost.amount = 0
                } else {
                    val extraCostValue = extraCost.toInt()
                    transaction.extraCost.amount = extraCostValue
                }

                // calculate total price
                calculateTotalPrice()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // add paid edit text text watcher listener
        et_paid.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(s: Editable?) {

                // get the paid value string
                val paidText = s.toString()

                // get paid int value
                val paidValue = if (paidText.isEmpty()) 0 else paidText.toInt()

                // set paid value to transaction model
                transaction.paid = paidValue

                calculateDueOrChangeValue()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        // btn add product click listener
        btn_add_product.setOnClickListener {
            showAvailableProductDialog()
        }

        // payment type spinner on change listener
        spinner_payment_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when(position) {

                    0 -> {
                        transaction.paymentType = Constant.TRANSACTION_PAYMENT_CASH
                        tv_ac_no_hint.visibility = View.GONE
                        et_ac_no.visibility = View.GONE
                    }

                    1 -> {
                        transaction.paymentType = Constant.TRANSACTION_PAYMENT_BANK
                        tv_ac_no_hint.text = getString(R.string.ac_number)
                        tv_ac_no_hint.visibility = View.VISIBLE
                        et_ac_no.visibility = View.VISIBLE
                    }

                    2 -> {
                        transaction.paymentType = Constant.TRANSACTION_PAYMENT_CHEQUE
                        tv_ac_no_hint.text = getString(R.string.cheque_number)
                        tv_ac_no_hint.visibility = View.VISIBLE
                        et_ac_no.visibility = View.VISIBLE
                    }
                }
            }
        }


        // btn done click listener
        btn_done_transaction.setOnClickListener {

            if (transaction.customerOrSupplierId == null) {
                ac_tv_customer_name.error = "Required"
                val message = if(transaction.transactionType == Constant.TRANSACTION_SELL) "No Customer Selected !!" else "No Supplier Selected !!"
                MySnackBar.instance.setMessage(message).setTextColor(Color.RED).show(this)
                return@setOnClickListener
            }

            if (transaction.products.isEmpty()) {
                MySnackBar.instance.setMessage("No Products Selected !!").setTextColor(Color.RED).show(this)
                return@setOnClickListener
            }

            if (transaction.paymentType != Constant.TRANSACTION_PAYMENT_CASH) {

                if (et_ac_no.text.toString().isEmpty()) {

                    val message = if (transaction.paymentType == Constant.TRANSACTION_PAYMENT_BANK) {
                        "Bank account number required"
                    } else {
                        "Cheque number required"
                    }

                    MySnackBar.instance.setMessage(message).setTextColor(Color.RED).show(this)
                    return@setOnClickListener
                }

                // set cheque or ac number to model
                transaction.chequeOrAcNo = et_ac_no.text.toString()
            }

            // set extra cost details
            transaction.extraCost.title = et_other_cost_name.text.toString()

            // set current date to transaction
            transaction.date = MyUtils.currentDateFormatted

            // pass the object to post into database
            mTransactionPresenter.onTransactionDoneClick(transaction)
        }
    }

    private fun removeAllPreviousSelection() {

        // remove all previous selection if transaction type changed
        mCartListAdapter.clear()

        // clear the customer search text
        ac_tv_customer_name.text.clear()

        transaction.extraCost.title = ""
        transaction.extraCost.amount = 0

        transaction.paid = 0
        transaction.totalPrice = 0
        transaction.due = 0
        transaction.change = 0

        et_other_cost_name.text.clear()
        et_other_cost_value.text.clear()
        et_paid.text.clear()

        val total = "${getString(R.string.bdt)} 0"
        tv_total_value.text = total
        tv_due_value.text = total

        tv_item_selected_count.visibility = View.GONE
        rv_product_cart_list.visibility = View.GONE
        tv_no_item.visibility = View.VISIBLE
    }

    /**
     * toolbar back pressed button click
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * finish this activity on back pressed click
     */
    override fun onBackPressed() {
        finish()
    }

    /**
     * show bottom sheet available product
     */
    private fun showAvailableProductDialog() {
        val bottomSheetAvailableProduct = BottomSheetAvailableProduct(this, transaction.transactionType!!, this)
        bottomSheetAvailableProduct.show()
    }

    /**
     * add selected cart product to list
     * this product come from add/update bottom sheet
     */
    override fun addProductToCartList(productCart: ProductCart) {

        if (tv_item_selected_count.visibility == View.GONE) {

            tv_item_selected_count.visibility = View.VISIBLE
            rv_product_cart_list.visibility = View.VISIBLE
            tv_no_item.visibility = View.GONE
        }

        if (transaction.products.contains(productCart)) {

            // update existing selected product
            val indexOf = transaction.products.indexOf(productCart)
            mCartListAdapter.update(productCart, indexOf)

        } else {

            // add selected product
            mCartListAdapter.add(productCart)

            // set the items count text
            val countText = "${mCartListAdapter.itemCount} Product Selected"
            tv_item_selected_count.text = countText
        }

        // calculate total price every time products added or updated
        calculateTotalPrice()
    }

    /**
     * calculate total price after products added, removed or extra money added
     */
    private fun calculateTotalPrice() {

        // calculate and set total price
        transaction.products.map { it.quantity.times(it.unitPrice) }.sum().let {

            // add extra cost also
            val totalPriceWithExtraCost = it.plus(transaction.extraCost.amount)

            val totalPriceValue = "${getString(R.string.bdt)} $totalPriceWithExtraCost"
            tv_total_value.text = totalPriceValue

            // set total value to transaction model
            transaction.totalPrice = totalPriceWithExtraCost

            // then calculate change or due value
            calculateDueOrChangeValue()
        }
    }

    /**
     * calculate if have any due or change after paid value updated
     */
    private fun calculateDueOrChangeValue() {

        // paid value and total value same so don't need to show due or change view
        if (transaction.paid == transaction.totalPrice && tv_due.visibility == View.VISIBLE) {

            tv_due.visibility = View.GONE
            tv_due_value.visibility = View.GONE

            transaction.due = 0

        } else {

            // paid value is greater then total value,
            // so show the return able change value
            if (transaction.paid > transaction.totalPrice) {

                tv_due.text = getString(R.string.change)

                // calculate change value
                val changeValue = transaction.totalPrice.minus(transaction.paid)

                val changeValueString = "${getString(R.string.bdt)} $changeValue"
                tv_due_value.text = changeValueString

                // set change value to transaction model
                transaction.change = changeValue

                // set due value to 0
                transaction.due = 0

            } else {

                // paid value the smaller than total value
                // so show the due value
                tv_due.text = getString(R.string.due)

                // calculate due value
                val dueValue = transaction.totalPrice.minus(transaction.paid)

                val dueValueString = "${getString(R.string.bdt)} $dueValue"
                tv_due_value.text = dueValueString

                // set due value to transaction model
                transaction.due = dueValue

                // set change value 0
                transaction.change = 0
            }

            // if view is gone then show the view first
            if (tv_due.visibility == View.GONE) {
                tv_due.visibility = View.VISIBLE
                tv_due_value.visibility = View.VISIBLE
            }
        }

    }

    /**
     * init selected cart list recycler view
     */
    private fun initCartListRecyclerView() {

        mCartListAdapter = object : RecyclerViewAdapter<ProductCart, OnCartProductListener>(transaction.products) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<ProductCart, OnCartProductListener> {
                return SelectedCartProductHolder(inflate(R.layout.item_transaction_cart_product, parent))
            }
        }

        rv_product_cart_list.adapter = mCartListAdapter

        mCartListAdapter.setListener(object : OnCartProductListener{

            override fun onCartProductRemove(position: Int, cartProduct: ProductCart) {

                val dialog = MyDialog(this@TransactionActivity)
                dialog.setMessage("Confirm to remove ${cartProduct.product?.brand} from cart ?")
                dialog.setNegativeButton("Cancel", object : MyDialog.OnNegativeListener{
                    override fun onNegative() {
                        dialog.dismiss()
                    }
                })

                dialog.setPositiveButton("Ok", object : MyDialog.OnPositiveListener{
                    override fun onPositive() {

                        // remove selected item
                        transaction.products.removeAt(position)
                        mCartListAdapter.notifyDataSetChanged()

                        // calculate total price again after product removed
                        calculateTotalPrice()

                        // show no items text if products list empty
                        if (transaction.products.isEmpty()) {

                            tv_item_selected_count.visibility = View.GONE
                            rv_product_cart_list.visibility = View.GONE
                            tv_no_item.visibility = View.VISIBLE

                        } else {

                            // items not empty yet, so change the items count
                            val countText = "${mCartListAdapter.itemCount} Product Selected"
                            tv_item_selected_count.text = countText
                        }

                        dialog.dismiss()
                    }
                })

                dialog.show()
            }

            override fun onItemClickListener(item: ProductCart, position: Int) {

                // show update cart product bottom sheet
                showUpdateCartProductBottomSheet(item)
            }
        })
    }

    /**
     * update selected product bottom sheet dialog
     */
    private fun showUpdateCartProductBottomSheet(item: ProductCart) {
        val bottomSheet = BottomSheetSelectedCartProduct(this, item, this)
        bottomSheet.show()
    }


    /**
     * transaction complete so finish this activity
     */
    override fun onTransactionComplete(success: Boolean) {

        if (success){
            finish()
        }
    }

    /**
     * set the customer list to autocomplete textview to show suggestion
     */
    override fun onCustomerSearchListLoaded(searchListItems: ArrayList<CustomerOrSupplierSearchItem>) {

        customerSearchAdapter.setDataList(searchListItems)
    }
}
