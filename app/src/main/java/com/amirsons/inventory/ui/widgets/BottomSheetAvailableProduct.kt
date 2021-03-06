package com.amirsons.inventory.ui.widgets

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearSnapHelper
import com.amirsons.inventory.R
import com.amirsons.inventory.app.Constant
import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.BrandProduct
import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.datamanager.model.ProductCart
import com.amirsons.inventory.event.OnProductItemClickedListener
import com.amirsons.inventory.ui.activity.transaction.TransactionActivityView
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.ui.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.ui.recyclerview.viewholder.BrandHolder
import com.amirsons.inventory.ui.recyclerview.viewholder.ProductHolder
import com.amirsons.inventory.utils.KeyboardUtils
import com.amirsons.inventory.utils.NestedRecyclerViewTouchListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.bottomsheet_available_product.*
import kotlinx.android.synthetic.main.content_transaction_selected_product_cart.*

/**
 * Created by Taohid on 10, July, 2019
 * Email: taohid32@gmail.com
 */
class BottomSheetAvailableProduct(context: Context, private val transactionType: String, private val transactionActivityView: TransactionActivityView) : MyBottomSheetDialog(context) {

    private lateinit var mProductAdapter: RecyclerViewAdapter<Product, OnProductItemClickedListener>
    private lateinit var mBrandAdapter: RecyclerViewAdapter<BrandProduct, BaseRecyclerClickListener<BrandProduct>>

    // default brand selected position
    private var defaultBrandPosition = -1
    private val brandNameList = ArrayList<String>()
    private lateinit var selectedProduct : Product

    override val getContentLayout: Int
        get() = R.layout.bottomsheet_available_product


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(false)

        // init recycler view
        initBrandRecyclerView()
        initProductRecyclerView()

        // init product selected text watcher
        initProductSelectedUnitPriceTextWatcher()

        loadProductsData()

        btn_add_new_product.setOnClickListener {
            showAddProductBottomSheet(brandNameList)
        }
    }

    /**
     * init the product add to cart view
     * add text change listener only for once,
     * so call this method only from on create
     */
    private fun initProductSelectedUnitPriceTextWatcher() {

        // add unit text watcher
        et_unit.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

                val unitText = s.toString()
                
                // for transaction sale, input quantity must not grater than available stock
                if (transactionType == Constant.TRANSACTION_SELL &&
                        unitText.isNotEmpty() && (unitText.toInt() == 0 || unitText.toInt() > selectedProduct.availableStock)){

                    et_unit.text.clear()
                    Toast.makeText(context, "Not available in stock", Toast.LENGTH_LONG).show()
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

        btn_add_cart.setOnClickListener {

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

            // create new cart product
            val productCart = ProductCart()

            // for sale current retail price may be updated 
            if (transactionType == Constant.TRANSACTION_SELL){
                productCart.isUpdatePrice = cb_update_retail.isChecked
            }
            
            productCart.product = selectedProduct
            productCart.productId = selectedProduct.productId
            productCart.brand = selectedProduct.brand
            productCart.quantity = unit.toInt()
            productCart.unitPrice = parUnitPrice.toInt()

            // pass the selected product cart list
            transactionActivityView.addProductToCartList(productCart)

            // hide the soft keyboard
            KeyboardUtils.hideSoftInput(et_per_unit_price)

            dismiss()
        }

        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    /**
     * get products data from database
     */
    private fun loadProductsData() {

        // add an event for add product listener
        DatabaseManager.getDatabaseRef(DatabaseNode.PRODUCT).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // clear previous brand name list
                brandNameList.clear()

                val brandList : ArrayList<BrandProduct> = ArrayList()

                // loop each brand name node then get product inside brand name node
                dataSnapshot.children.forEach { brandSnapshot ->

                    // get the brand name, brand name is the key of product
                    val brand = brandSnapshot.key

                    // temp list for each branded products
                    val productList : ArrayList<Product> = ArrayList()

                    // loop through brand name for get products
                    brandSnapshot?.children?.forEach { productSnapshot ->

                        // get single product object
                        val product = productSnapshot.getValue(Product::class.java)

                        // set the product name and id into model
                        product?.brand = brand
                        product?.productId = productSnapshot.key

                        // assume product not null
                        product?.let {

                            productList.add(0, it)
                        }
                    }

                    brand?.let {

                        // store brand name list to show suggestion on product add
                        brandNameList.add(it)

                        // finally add brand product with brand name
                        brandList.add(BrandProduct(it, productList))
                    }
                }

                setBrandListToView(brandList)
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })

    }

    /**
     * init the brand list recycler view
     */
    private fun initBrandRecyclerView() {

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_brand_name_list)

        mBrandAdapter = object : RecyclerViewAdapter<BrandProduct, BaseRecyclerClickListener<BrandProduct>>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<BrandProduct, BaseRecyclerClickListener<BrandProduct>> {
                return BrandHolder(inflate(R.layout.item_brand, parent))
            }
        }
        rv_brand_name_list.adapter = mBrandAdapter

        mBrandAdapter.setListener(object : BaseRecyclerClickListener<BrandProduct> {

            override fun onItemClickListener(item: BrandProduct, position: Int) {

                if (defaultBrandPosition != -1 && position != defaultBrandPosition){
                    mBrandAdapter.getItem(defaultBrandPosition).isSelected = false
                    mBrandAdapter.notifyItemChanged(defaultBrandPosition)
                }

                // set the product list
                mProductAdapter.setItems(item.productList)

                // store current selected position
                defaultBrandPosition = position

                rv_brand_name_list.scrollToPosition(position)
            }
        })
    }

    /**
     * int product recycler view
     */
    private fun initProductRecyclerView() {

        mProductAdapter = object : RecyclerViewAdapter<Product, OnProductItemClickedListener>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Product, OnProductItemClickedListener> {
                return ProductHolder(inflate(R.layout.item_inventory, parent))
            }
        }
        rv_available_product_list.adapter = mProductAdapter

        // set nested scroll enable by customizing item touch listener
        rv_available_product_list.addOnItemTouchListener(NestedRecyclerViewTouchListener.mScrollTouchListener)

        // add item selected listener
        mProductAdapter.setListener(object : OnProductItemClickedListener {

            override fun onItemClickListener(item: Product, position: Int) {

                selectedProduct = item

                // show add product to cart view
                showSelectedProductView()
            }
        })
    }

    /**
     * set brand to to recycler view
     */
    private fun setBrandListToView(brandList: ArrayList<BrandProduct>) {

        if (brandList.isEmpty()) {

            tv_no_item.visibility = View.VISIBLE
            mBrandAdapter.clear()
            mProductAdapter.clear()
            return

        } else {

            tv_no_item.visibility = View.GONE
        }

        mBrandAdapter.setItems(brandList)

        // set default product list to first item
        if (defaultBrandPosition == -1) {
            defaultBrandPosition = 0
        }

        brandList[defaultBrandPosition].isSelected = true
        mProductAdapter.setItems(brandList[defaultBrandPosition].productList)
    }

    /**
     * show selected product bottom sheet view
     */
    private fun showSelectedProductView() {

        // clear previous unit text
        et_unit.text.clear()

        // show keyboard for unit
        KeyboardUtils.showSoftInput(et_unit)
        
        if (transactionType == Constant.TRANSACTION_BUY) {
            
            // change the header title
            tv_header_title.text = context.getString(R.string.product_buying)
            
            // change the unit price to buying price
            tv_per_price_hint.text = context.getString(R.string.buy_price)

            // hide the update retail price check box
            cb_update_retail.visibility = View.GONE

        } else {

            // change the header title
            tv_header_title.text = context.getString(R.string.product_selling)

            // change the unit price to buying price
            tv_per_price_hint.text = context.getString(R.string.sell_price)

            // for transaction type sell set per unit price text
            et_per_unit_price.setText(selectedProduct.retailPrice.toString())
        }

        val size = "(${selectedProduct.size})"
        val availableCount = "${selectedProduct.availableStock} unit"

        tv_cart_item_category.text = selectedProduct.category
        tv_cart_item_size.text = size
        tv_cart_item_brand_name.text = selectedProduct.brand
        tv_cart_product_weight.text = selectedProduct.weight
        tv_cart_item_available_unit.text = availableCount

        // finally show the product selected view
        view_switcher.showNext()
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

    /**
     * add product bottom sheet dialog
     */
    private fun showAddProductBottomSheet(brandNameList: ArrayList<String>) {

        // show add product bottom sheet
        val addProductBottomSheet = BottomSheetAddProduct(context, brandNameList, object : OnAddProductConfirm {

            override fun onConfirm(product: Product): Boolean {

                product.brand?.let { DatabaseManager.add(product, DatabaseNode.PRODUCT, it)}
                return true
            }
        })

        // show the bottom sheet dialog
        addProductBottomSheet.show()
    }

    override fun onBackPressed() {

        if (view_switcher.displayedChild == 1){

            view_switcher.showPrevious()

        } else{
            super.onBackPressed()
        }
    }
}