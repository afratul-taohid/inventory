package com.cliniva.enventory.ui.add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.cliniva.enventory.R;
import com.cliniva.enventory.adapter.AvailableProductListAdapter;
import com.cliniva.enventory.model.AvailableProductList;
import com.cliniva.enventory.widgets.ClinivaDialog;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private Button addProduct;
    private Button saleProduct;
    private Button buyProduct;
    private Button addProductDone;

    private ImageButton addAvailableProduct;

    List<AvailableProductList> list_product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        addProduct = (Button)findViewById(R.id.btn_add_product);
        saleProduct = (Button)findViewById(R.id.btn_sale);
        buyProduct = (Button)findViewById(R.id.btn_buy);
        addProductDone = (Button)findViewById(R.id.btn_add_product_done);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductDialog();
            }
        });
    }

    private void showAddProductDialog() {

        View dialogView = LayoutInflater.from(this).inflate(R.layout.available_product, null, false);

        ClinivaDialog clinivaDialog = new ClinivaDialog(this, dialogView);

        addAvailableProduct = dialogView.findViewById(R.id.btn_add_product_available);

        addAvailableProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvailableProduct();
            }

        });
        RelativeLayout availableProductHeader = dialogView.findViewById(R.id.rl_available_product_list);

        list_product = new ArrayList<>();
        AvailableProductList availableProductList = new AvailableProductList("Hourse", "C/A", "300 gsm");
        list_product.add(availableProductList);
        list_product.add(availableProductList);
        list_product.add(availableProductList);

        RecyclerView recyclerView = dialogView.findViewById(R.id.rl_available_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AvailableProductListAdapter availableProductListAdapter = new AvailableProductListAdapter(list_product, this);
        recyclerView.setAdapter(availableProductListAdapter);
        availableProductListAdapter.setOnAddButtonListener(new AvailableProductListAdapter.OnAddButtonListener() {
            @Override
            public void onButtonClick(int position) {
                clinivaDialog.dismiss();
            }
        });

      //  AvailableProductListAdapter availableProductListAdapter = new AvailableProductListAdapter

//        RelativeLayout rlHeader = dialogView.findViewById(R.id.rl_header);
//        RelativeLayout rlContent = dialogView.findViewById(R.id.rl_content);
//
//        rlHeader.setOnClickListener(v -> {
//
//            if (rlContent.getVisibility() == View.GONE){
//                ViewUtils.expand(rlContent);
//            } else {
//                ViewUtils.collapse(rlContent);
//            }
//        });


        clinivaDialog.show();
    }

    private void showAvailableProduct() {

        View productView = LayoutInflater.from(this).inflate(R.layout.dialog_add_product, null, false);

        RelativeLayout addProductHeader = productView.findViewById(R.id.rl_header);

        RelativeLayout addProductContent = productView.findViewById(R.id.rl_content);

        ClinivaDialog clinivaDialog = new ClinivaDialog(this, productView);

        clinivaDialog.show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
