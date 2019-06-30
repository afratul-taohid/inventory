package com.cliniva.enventory.ui.customer;


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
import com.cliniva.enventory.event.OnCustomerItemClickedListener;
import com.cliniva.enventory.model.Customer;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.viewholder.CustomerHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends BaseFragment implements CustomerContract.View, OnCustomerItemClickedListener {

    private RecyclerView mCustomerList;
    private CustomerContract.Presenter mCustomerPresenter;

    public CustomerFragment() {
        // Required empty public constructor
    }

    public static CustomerFragment getInstance(){
        CustomerFragment fragment = new CustomerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_customer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomerPresenter = new CustomerPresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view, mCustomerPresenter.getContext().getString(R.string.action_customer), false);

        mCustomerList = view.findViewById(R.id.rv_customer_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mCustomerPresenter.getContext());
        mCustomerList.setLayoutManager(layoutManager);
        mCustomerPresenter.onLoadList();
    }

    @Override
    public void setListToView(List<Customer> customerList) {
        RecyclerViewAdapter<Customer, OnCustomerItemClickedListener> mCustomerAdapter = new RecyclerViewAdapter<Customer, OnCustomerItemClickedListener>(customerList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<Customer, OnCustomerItemClickedListener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mCustomerPresenter.getContext()), R.layout.item_customer, parent, false);
                return new CustomerHolder(binding);
            }
        };
        mCustomerAdapter.setListener(this);
        mCustomerList.setAdapter(mCustomerAdapter);
    }

    @Override
    public void onSingleViewClicked(View v, int position) {

    }

    @Override
    public void onItemClickListener(Customer item, int position) {

    }
}
