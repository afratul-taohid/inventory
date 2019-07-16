package com.amirsons.inventory.ui.fragment.supplier


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.amirsons.inventory.R
import com.amirsons.inventory.datamanager.model.Customer
import com.amirsons.inventory.datamanager.model.Supplier
import com.amirsons.inventory.event.OnSupplierItemClickedListener
import com.amirsons.inventory.ui.recyclerview.base.BaseRecyclerViewHolder
import com.amirsons.inventory.ui.recyclerview.base.RecyclerViewAdapter
import com.amirsons.inventory.ui.recyclerview.viewholder.SupplierHolder
import com.amirsons.inventory.ui.base.BaseFragment
import com.amirsons.inventory.ui.widgets.BottomSheetAddCustomer
import com.amirsons.inventory.ui.widgets.OnAddCustomerConfirm
import kotlinx.android.synthetic.main.fragment_supplier.*
import java.util.*

/**
 * A simple [BaseFragment] subclass.
 */
class SupplierFragment : BaseFragment(), SupplierView {

    private lateinit var mSupplierPresenter: SupplierPresenter
    private lateinit var mSupplierAdapter: RecyclerViewAdapter<Supplier, OnSupplierItemClickedListener>

    override val contentLayout: Int
        get() = R.layout.fragment_supplier


    companion object {
        val instance: SupplierFragment
            get() {
                val fragment = SupplierFragment()
                val args = Bundle()
                fragment.arguments = args
                return fragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSupplierPresenter = SupplierMvp(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(view, mSupplierPresenter.context.getString(R.string.action_suppliers), false)

        initSupplierListAdapter()

        mSupplierPresenter.onLoadSupplierList()

        btn_fab.setOnClickListener {

            BottomSheetAddCustomer(context, "Add Supplier Details", object : OnAddCustomerConfirm {

                override fun onConfirm(item: Customer): Boolean {

                    val supplier = Supplier()
                    supplier.name = item.name
                    supplier.address = item.address
                    supplier.mobile = item.mobile

                    // add new customer
                    mSupplierPresenter.onAddSupplierClick(supplier)

                    // return true to dismiss the dialog
                    return true
                }

            }).show()
        }
    }

    private fun initSupplierListAdapter() {

        mSupplierAdapter = object : RecyclerViewAdapter<Supplier, OnSupplierItemClickedListener>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Supplier, OnSupplierItemClickedListener> {
                return SupplierHolder(inflate(R.layout.item_supplier, parent))
            }
        }

        rv_supplier_list.adapter = mSupplierAdapter
    }

    override fun setSupplierToView(supplierList: ArrayList<Supplier>) {
        mSupplierAdapter.setItems(supplierList)
    }
}
