package com.cliniva.enventory.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.R;
import com.cliniva.enventory.listdata.AvailableProductList;
import com.cliniva.enventory.viewholder.AvailableProductListViewHolder;

import java.util.List;

public class AvailableProductListAdapter extends RecyclerView.Adapter<AvailableProductListViewHolder> {

    private List<AvailableProductList> list_product;
    private Context context_product;
    private OnAddButtonListener onAddButtonListener;

    public interface OnAddButtonListener{
        void onButtonClick(int position);
    }

    public void setOnAddButtonListener(OnAddButtonListener onAddButtonListener) {
        this.onAddButtonListener = onAddButtonListener;
    }

    public AvailableProductListAdapter(List<AvailableProductList> list_product, Context context_product) {
        this.list_product = list_product;
        this.context_product = context_product;
    }

    @NonNull
    @Override
    public AvailableProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View viewProduct = LayoutInflater.from(context_product).inflate(R.layout.item_available_product,viewGroup,false);

        return new AvailableProductListViewHolder(viewProduct);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableProductListViewHolder availableProductListViewHolder, int position) {

        AvailableProductList availableProductList = list_product.get(position);

        availableProductListViewHolder.productName.setText(availableProductList.getProduct_name());
        availableProductListViewHolder.productType.setText(availableProductList.getProduct_type());
        availableProductListViewHolder.productWeight.setText(availableProductList.getProduct_weight());

        availableProductListViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onAddButtonListener != null){
                    onAddButtonListener.onButtonClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list_product.size();
    }
}
