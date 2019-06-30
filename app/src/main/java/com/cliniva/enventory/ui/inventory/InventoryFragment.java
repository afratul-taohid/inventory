package com.cliniva.enventory.ui.inventory;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.R;
import com.cliniva.enventory.model.Brand;
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.ui.base.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends BaseFragment implements InventoryContract.View {

    private InventoryContract.Presenter mInventoryPresenter;

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
    }

    @Override
    public void setBrandListToView(List<Brand> brandList) {

    }

    @Override
    public void setProductListToView(List<Product> productList) {

    }
}
