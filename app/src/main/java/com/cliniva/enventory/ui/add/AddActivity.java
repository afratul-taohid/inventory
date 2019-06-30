package com.cliniva.enventory.ui.add;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cliniva.enventory.R;
import com.cliniva.enventory.adapter.RecyclerViewAdapter;
import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.app.InventoryApp;
import com.cliniva.enventory.model.AddProduct;
import com.cliniva.enventory.ui.base.BaseActivity;
import com.cliniva.enventory.viewholder.AvailableProductHolder;
import com.cliniva.enventory.widgets.ClinivaBottomSheetDialog;
import com.cliniva.enventory.widgets.ClinivaDialog;

import java.util.List;

public class AddActivity extends BaseActivity implements AddContract.View, View.OnClickListener, BaseRecyclerClickListener<AddProduct> {

    private Button addProduct;
    private Button saleProduct;
    private Button buyProduct;
    private Button addProductDone;

    private RecyclerView mAddProductList;
    private AddContract.Presenter mAddPresenter;
    private TextView mSelectedCountView;

    private ImageButton addAvailableProduct;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_add;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAddPresenter = new AddPresenter(this);

        addProduct = findViewById(R.id.btn_add_product);
        saleProduct = findViewById(R.id.btn_sale);
        buyProduct = findViewById(R.id.btn_buy);
        addProductDone = findViewById(R.id.btn_add_product_done);
        mAddProductList = findViewById(R.id.rv_add_product_list);
        mSelectedCountView = findViewById(R.id.tv_item_selected_count);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAddProductList.setLayoutManager(layoutManager);
        mAddPresenter.onLoadList();

        addProduct.setOnClickListener(this);
    }

    private void showAddProductDialog() {
        ClinivaBottomSheetDialog bottomSheetDialog = new ClinivaBottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.available_product);
        bottomSheetDialog.show();
    }

    private void showAvailableProduct() {

        View productView = LayoutInflater.from(this).inflate(R.layout.dialog_add_product, null, false);
        ClinivaDialog clinivaDialog = new ClinivaDialog(this, productView);
        clinivaDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_product:
                showAddProductDialog();
                break;
        }
    }

    @Override
    public void setListToView(List<AddProduct> productList) {
        RecyclerViewAdapter<AddProduct, BaseRecyclerClickListener<AddProduct>> mProductAdapter = new RecyclerViewAdapter<AddProduct, BaseRecyclerClickListener<AddProduct>>(productList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<AddProduct, BaseRecyclerClickListener<AddProduct>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mAddPresenter.getContext()), R.layout.item_add_product, parent, false);
                return new AvailableProductHolder(binding);
            }
        };
        mProductAdapter.setListener(this);
        mAddProductList.setAdapter(mProductAdapter);
        mSelectedCountView.setText(String.format("%s item selected", productList.size()));
    }

    @Override
    public InventoryApp getApp() {
        return inventoryApp;
    }

    @Override
    public void onItemClickListener(AddProduct item, int position) {

    }
}
