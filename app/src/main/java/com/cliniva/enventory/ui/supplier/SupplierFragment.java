package com.cliniva.enventory.ui.supplier;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.R;
import com.cliniva.enventory.adapter.RecyclerViewAdapter;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.listener.OnSupplierItemClickedListener;
import com.cliniva.enventory.model.Customer;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.viewholder.SupplierHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplierFragment extends BaseFragment implements SupplierContract.View, OnSupplierItemClickedListener {

    private RecyclerView mSupplierList;
    private SupplierContract.Presenter mSupplierPresenter;

    public SupplierFragment() {
        // Required empty public constructor
    }

    public static SupplierFragment getInstance(){
        SupplierFragment fragment = new SupplierFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_supplier;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSupplierPresenter = new SupplierPresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view, mSupplierPresenter.getContext().getString(R.string.action_suppliers), false);
        mSupplierList = view.findViewById(R.id.rv_supplier_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mSupplierPresenter.getContext());
        mSupplierList.setLayoutManager(layoutManager);
        mSupplierPresenter.onLoadList();
    }

    @Override
    public void setListToView(List<Customer> supplierList) {
        RecyclerViewAdapter<Customer, OnSupplierItemClickedListener> mSupplierAdapter = new RecyclerViewAdapter<Customer, OnSupplierItemClickedListener>(supplierList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<Customer, OnSupplierItemClickedListener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mSupplierPresenter.getContext()), R.layout.item_supplier, parent, false);
                return new SupplierHolder(binding);
            }
        };
        mSupplierAdapter.setListener(this);
        mSupplierList.setAdapter(mSupplierAdapter);
    }

    @Override
    public void onSingleViewClicked(View item, int position) {

    }

    @Override
    public void onItemClickListener(Customer item, int position) {

    }
}
