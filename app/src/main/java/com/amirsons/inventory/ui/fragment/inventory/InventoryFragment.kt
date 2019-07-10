package com.amirsons.inventory.ui.fragment.inventory

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.BrandProduct
import com.amirsons.inventory.datamanager.model.Product
import com.amirsons.inventory.event.OnProductItemClickedListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerClickListener
import com.amirsons.inventory.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.recyclerview.viewholder.BrandHolder
import com.amirsons.inventory.recyclerview.viewholder.ProductHolder
import com.amirsons.inventory.ui.base.BaseFragment
import com.amirsons.inventory.ui.widgets.BottomSheetAddProduct
import com.amirsons.inventory.ui.widgets.OnAddProductConfirm
import kotlinx.android.synthetic.main.fragment_inventory.*

/**
 * A simple [BaseFragment] subclass.
 */
class InventoryFragment : BaseFragment(), InventoryView {

    private lateinit var mProductAdapter: RecyclerViewAdapter<Product, OnProductItemClickedListener>
    private lateinit var mBrandAdapter: RecyclerViewAdapter<BrandProduct, BaseRecyclerClickListener<BrandProduct>>
    private lateinit var mInventoryPresenter: InventoryPresenter

    private var defaultBrandPosition = -1

    override val contentLayout: Int
        get() = R.layout.fragment_inventory

    companion object {

        val instance: InventoryFragment
            get() {
                val fragment = InventoryFragment()
                val args = Bundle()
                fragment.arguments = args
                return fragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInventoryPresenter = InventoryMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set toolbar name
        setToolbar(view, mInventoryPresenter.context.getString(R.string.action_inventory), false)

        // init recycler view
        initBrandRecyclerView()
        initProductRecyclerView()

        // load the product list
        mInventoryPresenter.onLoadProductList()

        // fab button for add product
        btn_fab.setOnClickListener {
            mInventoryPresenter.onAddProductClick()
        }
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
        rv_product_list.adapter = mProductAdapter
    }

    override fun setBrandListToView(brandList: ArrayList<BrandProduct>) {

        mBrandAdapter.setItems(brandList)

        // set default product list to first item
        if (defaultBrandPosition == -1) {
            defaultBrandPosition = 0
        }

        brandList[defaultBrandPosition].isSelected = true
        mProductAdapter.setItems(brandList[defaultBrandPosition].productList)
    }


    override fun showAddProductBottomSheet(brandNameList: ArrayList<String>) {

        // show add product bottom sheet
        val addProductBottomSheet = BottomSheetAddProduct(context, brandNameList, object : OnAddProductConfirm {

            override fun onConfirm(product: Product): Boolean {

                mInventoryPresenter.onAddProduct(product)
                return true
            }
        })

        // show the bottom sheet dialog
        addProductBottomSheet.show()
    }
}
