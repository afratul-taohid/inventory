package com.cliniva.enventory.ui.sales;


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
import com.cliniva.enventory.event.OnProductItemClickedListener;
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.viewholder.ProductHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment extends BaseFragment implements SalesContract.View, OnProductItemClickedListener {

    private RecyclerView mProductList;
    private SalesContract.Presenter mProductPresenter;

    public SalesFragment() {
        // Required empty public constructor
    }

    public static SalesFragment getInstance(){
        SalesFragment fragment = new SalesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sales;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductPresenter = new SalesPresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProductList = view.findViewById(R.id.rv_sales_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mProductList.setLayoutManager(layoutManager);
        mProductPresenter.onLoadList();
    }

    @Override
    public void setListToView(List<Product> productList) {

        RecyclerViewAdapter<Product, OnProductItemClickedListener> mProductAdapter = new RecyclerViewAdapter<Product, OnProductItemClickedListener>(productList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<Product, OnProductItemClickedListener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.item_inventory, parent, false);
                return new ProductHolder(binding);
            }
        };
        mProductAdapter.setListener(this);
        mProductList.setAdapter(mProductAdapter);
    }

    @Override
    public void onSingleViewClicked(View v, int position) {

    }

    @Override
    public void onItemClickListener(Product item, int position) {

    }
}
