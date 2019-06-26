package com.cliniva.enventory.ui.product;


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

import com.android.databinding.library.baseAdapters.DataBinderMapperImpl;
import com.cliniva.enventory.R;
import com.cliniva.enventory.adapter.RecyclerViewAdapter;
import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.listener.OnProductItemClickedListener;
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.viewholder.ProductHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends BaseFragment implements ProductContract.View, OnProductItemClickedListener {

    private RecyclerView mProductList;
    private ProductContract.Presenter mProductPresenter;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment getInstance(){
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_product;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductPresenter = new ProductPresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view, mProductPresenter.getContext().getString(R.string.action_product), false);
        mProductList = view.findViewById(R.id.rv_product_list);

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
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.item_product, parent, false);
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
