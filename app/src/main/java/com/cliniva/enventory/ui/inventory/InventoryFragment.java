package com.cliniva.enventory.ui.inventory;


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
import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.event.OnProductItemClickedListener;
import com.cliniva.enventory.model.Brand;
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.viewholder.BrandHolder;
import com.cliniva.enventory.viewholder.ProductHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends BaseFragment implements InventoryContract.View, BaseRecyclerClickListener<Brand> {

    private InventoryContract.Presenter mInventoryPresenter;
    private RecyclerView mProductList;
    private RecyclerView mBrandList;

    public InventoryFragment() {
        // Required empty public constructor
    }

    public static InventoryFragment getInstance(){
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_inventory;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInventoryPresenter = new InventoryPresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view, mInventoryPresenter.getContext().getString(R.string.action_inventory), false);

        mProductList = view.findViewById(R.id.rv_product_list);
        mBrandList = view.findViewById(R.id.rv_brand_name_list);
        LinearLayoutManager verticalManager = new LinearLayoutManager(mInventoryPresenter.getContext());
        mProductList.setLayoutManager(verticalManager);
        LinearLayoutManager horizontalManager = new LinearLayoutManager(mInventoryPresenter.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBrandList.setLayoutManager(horizontalManager);
        mInventoryPresenter.onLoadList();
    }

    @Override
    public void setBrandListToView(List<Brand> brandList) {
        RecyclerViewAdapter<Brand, BaseRecyclerClickListener<Brand>> mBrandAdapter = new RecyclerViewAdapter<Brand, BaseRecyclerClickListener<Brand>>(brandList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<Brand, BaseRecyclerClickListener<Brand>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_brand, parent, false);
                return new BrandHolder(binding);
            }
        };
        mBrandAdapter.setListener(this);
        mBrandList.setAdapter(mBrandAdapter);
    }

    @Override
    public void setProductListToView(List<Product> productList) {
        RecyclerViewAdapter<Product, OnProductItemClickedListener> mProductAdapter = new RecyclerViewAdapter<Product, OnProductItemClickedListener>(productList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<Product, OnProductItemClickedListener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_inventory, parent, false);
                return new ProductHolder(binding);
            }
        };

        mProductAdapter.setListener(new OnProductItemClickedListener() {
            @Override
            public void onSingleViewClicked(View v, int position) {

            }

            @Override
            public void onItemClickListener(Product item, int position) {

            }
        });
        mProductList.setAdapter(mProductAdapter);
    }

    @Override
    public void onItemClickListener(Brand item, int position) {

    }
}
