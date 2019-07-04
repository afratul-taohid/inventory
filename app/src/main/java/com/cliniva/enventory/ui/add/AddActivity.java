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
import com.cliniva.enventory.model.Brand;
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.ui.base.BaseActivity;
import com.cliniva.enventory.ui.main.MainActivity;
import com.cliniva.enventory.utils.IntentUtils;
import com.cliniva.enventory.viewholder.AddProductHolder;
import com.cliniva.enventory.viewholder.AvailableProductHolder;
import com.cliniva.enventory.viewholder.BrandHolder;
import com.cliniva.enventory.viewholder.ProductHolder;
import com.cliniva.enventory.widgets.ClinivaBottomSheetDialog;
import com.cliniva.enventory.widgets.ClinivaDialog;

import java.util.List;

public class AddActivity extends BaseActivity implements AddContract.View, View.OnClickListener, BaseRecyclerClickListener<AddProduct> {

    private Button addProduct;
    private Button saleProduct;
    private Button buyProduct;
    private Button addProductDone;

    private RecyclerView mAddProductView, mBrandView, mAvailableProductView;
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
        mAddProductView = findViewById(R.id.rv_add_product_list);
        mSelectedCountView = findViewById(R.id.tv_item_selected_count);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAddProductView.setLayoutManager(layoutManager);
        mAddPresenter.onLoadList();

        addProduct.setOnClickListener(this);
    }

    private void showAddProductDialog() {

        ClinivaBottomSheetDialog bottomSheetDialog = new ClinivaBottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_available_product);

        mBrandView = bottomSheetDialog.findViewById(R.id.rv_brand_name_list);
        mAvailableProductView = bottomSheetDialog.findViewById(R.id.rv_available_product_list);

        Button mBtnAddNew = bottomSheetDialog.findViewById(R.id.btn_add_new_product);
        mBtnAddNew.setOnClickListener(this);

        LinearLayoutManager mBrandLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager mAvailableLayoutManager = new LinearLayoutManager(this);
        mBrandView.setLayoutManager(mBrandLayoutManager);
        mAvailableProductView.setLayoutManager(mAvailableLayoutManager);

        mAddPresenter.onLoadAvailable();
        mAddPresenter.onLoadBrand();

        bottomSheetDialog.show();
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
            case R.id.btn_add_new_product:
                showNewAddProductDialog();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        IntentUtils.getInstance().onActivityIntentWithoutExtras(this, MainActivity.class);
    }

    private void showNewAddProductDialog() {
        ClinivaBottomSheetDialog bottomSheetDialog = new ClinivaBottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_add_product);
        bottomSheetDialog.show();
    }

    @Override
    public void setListToView(List<AddProduct> productList) {
        RecyclerViewAdapter<AddProduct, BaseRecyclerClickListener<AddProduct>> mProductAdapter = new RecyclerViewAdapter<AddProduct, BaseRecyclerClickListener<AddProduct>>(productList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<AddProduct, BaseRecyclerClickListener<AddProduct>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_add_product, parent, false);
                return new AddProductHolder(binding);
            }
        };
        mProductAdapter.setListener(this);
        mAddProductView.setAdapter(mProductAdapter);
        mSelectedCountView.setText(String.format("%s item selected", productList.size()));
    }

    @Override
    public void setAvailableList(List<Product> availableList) {
        RecyclerViewAdapter<Product, BaseRecyclerClickListener<Product>> mAvailableAdapter = new RecyclerViewAdapter<Product, BaseRecyclerClickListener<Product>>(availableList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<Product, BaseRecyclerClickListener<Product>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_available_product, parent, false);
                return new AvailableProductHolder(binding);
            }
        };
        mAvailableProductView.setAdapter(mAvailableAdapter);
    }

    @Override
    public void setBrandList(List<Brand> brandList) {
        RecyclerViewAdapter<Brand, BaseRecyclerClickListener<Brand>> mBrandAdapter = new RecyclerViewAdapter<Brand, BaseRecyclerClickListener<Brand>>(brandList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<Brand, BaseRecyclerClickListener<Brand>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_brand, parent, false);
                return new BrandHolder(binding);
            }
        };
        mBrandView.setAdapter(mBrandAdapter);
    }

    @Override
    public InventoryApp getApp() {
        return inventoryApp;
    }

    @Override
    public void onItemClickListener(AddProduct item, int position) {

    }
}
