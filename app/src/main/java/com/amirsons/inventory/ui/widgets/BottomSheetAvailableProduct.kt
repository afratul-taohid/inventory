package com.amirsons.inventory.ui.widgets

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.firebase.DatabaseManager
import com.amirsons.inventory.datamanager.firebase.DatabaseNode
import com.amirsons.inventory.datamanager.model.BrandProduct
import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.event.OnProductItemClickedListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.recyclerview.viewholder.BrandHolder
import com.amirsons.inventory.recyclerview.viewholder.ProductHolder
import com.amirsons.inventory.utils.NestedRecyclerViewTouchListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.bottomsheet_available_product.*
import kotlinx.android.synthetic.main.fragment_inventory.rv_brand_name_list


/**
 * Created by Taohid on 10, July, 2019
 * Email: taohid32@gmail.com
 */
class BottomSheetAvailableProduct(context: Context) : MyBottomSheetDialog(context) {

    private lateinit var mProductAdapter: RecyclerViewAdapter<Product, OnProductItemClickedListener>
    private lateinit var mBrandAdapter: RecyclerViewAdapter<BrandProduct, BaseRecyclerClickListener<BrandProduct>>
    private var defaultBrandPosition = -1
    private val brandNameList = ArrayList<String>()

    override val getContentLayout: Int
        get() = R.layout.bottomsheet_available_product


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init recycler view
        initBrandRecyclerView()
        initProductRecyclerView()

        loadProductsData()

        btn_add_new_product.setOnClickListener {
            showAddProductBottomSheet(brandNameList)
        }
    }

    private fun loadProductsData(){

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

                        // assume product not null
                        product?.let {
                            // set the product name into model
                            product.brand = brand
                            productList.add(0, product)
                        }
                    }

                    brand?.let {

                        // store brand name list to show suggestion on product add
                        brandNameList.add(brand)

                        // finally add brand product with brand name
                        brandList.add(BrandProduct(brand, productList))
                    }
                }

                if (brandList.isNotEmpty()){
                    setBrandListToView(brandList)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })

    }

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
    private fun initProductRecyclerView() {

        mProductAdapter = object : RecyclerViewAdapter<Product, OnProductItemClickedListener>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Product, OnProductItemClickedListener> {
                return ProductHolder(inflate(R.layout.item_inventory, parent))
            }
        }
        rv_available_product_list.adapter = mProductAdapter
        rv_available_product_list.addOnItemTouchListener(NestedRecyclerViewTouchListener.mScrollTouchListener)
    }

    private fun setBrandListToView(brandList: ArrayList<BrandProduct>) {

        mBrandAdapter.setItems(brandList)

        // set default product list to first item
        if (defaultBrandPosition == -1) {
            defaultBrandPosition = 0
        }

        brandList[defaultBrandPosition].isSelected = true
        mProductAdapter.setItems(brandList[defaultBrandPosition].productList)
    }


    private fun showAddProductBottomSheet(brandNameList: ArrayList<String>) {

        // show add product bottom sheet
        val addProductBottomSheet = BottomSheetAddProduct(context, brandNameList, object : OnAddProductConfirm {

            override fun onConfirm(product: Product): Boolean {

                DatabaseManager.add(product, DatabaseNode.PRODUCT, product.brand!!)
                return true
            }
        })

        // show the bottom sheet dialog
        addProductBottomSheet.show()
    }
}