package com.cliniva.enventory.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.R;
import com.cliniva.enventory.listdata.AddProductList;
import com.cliniva.enventory.viewholder.AddProductListViewHolder;

import java.util.List;

public class AddProductListAdapter extends RecyclerView.Adapter<AddProductListViewHolder> {

    private List<AddProductList> list_add_product;
    private Context context_add_product;


    public AddProductListAdapter(List<AddProductList> list_add_product, Context context_add_product) {
        this.list_add_product = list_add_product;
        this.context_add_product = context_add_product;
    }

    @NonNull
    @Override
    public AddProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View viewAddProduct = LayoutInflater.from(context_add_product).inflate(R.layout.add_product_list,viewGroup,false);

        return new AddProductListViewHolder(viewAddProduct);
    }

    @Override
    public void onBindViewHolder(@NonNull AddProductListViewHolder addProductListViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list_add_product.size();
    }
}
