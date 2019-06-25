package com.cliniva.enventory.ui.product;


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
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.viewholder.ProductHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends BaseFragment {

    private RecyclerView mProductList;

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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProductList = view.findViewById(R.id.rv_product_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mProductList.setLayoutManager(layoutManager);

        List<Product> products = new ArrayList<>();

        RecyclerViewAdapter<Product, BaseRecyclerClickListener<Product>> recyclerViewAdapter = new RecyclerViewAdapter<Product, BaseRecyclerClickListener<Product>>(products) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<Product, BaseRecyclerClickListener<Product>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                return new ProductHolder(inflate(R.layout.item_product, parent));
            }
        };

        recyclerViewAdapter.setListener(new BaseRecyclerClickListener<Product>() {
            @Override
            public void onItemClickListener(Product item, int position) {

            }
        });
    }
}
