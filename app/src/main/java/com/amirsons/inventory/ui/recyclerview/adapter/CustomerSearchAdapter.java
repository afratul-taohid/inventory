package com.amirsons.inventory.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amirsons.inventory.R;
import com.amirsons.inventory.datamanager.model.CustomerOrSupplierSearchItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomerSearchAdapter extends ArrayAdapter<CustomerOrSupplierSearchItem> {

    private List<CustomerOrSupplierSearchItem> dataList;
    private List<CustomerOrSupplierSearchItem> dataListAllItems;
    private int itemLayout;

    public CustomerSearchAdapter(Context context, int resource, List<CustomerOrSupplierSearchItem> storeDataLst) {
        super(context, resource, storeDataLst);
        dataList = storeDataLst;
        itemLayout = resource;
    }

    public void setDataList(List<CustomerOrSupplierSearchItem> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public CustomerOrSupplierSearchItem getItem(int position) {
        return dataList.get(position);
    }

    @NotNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        }

        TextView strName = view.findViewById(R.id.tv_text);
        strName.setText(dataList.get(position).getName());

        TextView strMobile = view.findViewById(R.id.tv_number);
        strMobile.setText(dataList.get(position).getMobile());

        View dividerView = view.findViewById(R.id.item_divider);

        if (position == dataList.size()-1){
            dividerView.setVisibility(View.GONE);
        } else {
            dividerView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        Object lock = new Object();

        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence prefix) {

                FilterResults results = new FilterResults();

                if (dataListAllItems == null) {
                    synchronized (lock) {
                        dataListAllItems = new ArrayList<>(dataList);
                    }
                }

                if (prefix == null || prefix.length() == 0) {

                    synchronized (lock) {
                        results.values = dataListAllItems;
                        results.count = dataListAllItems.size();
                    }

                } else {

                    final String searchStrLowerCase = prefix.toString().toLowerCase();

                    ArrayList<CustomerOrSupplierSearchItem> matchValues = new ArrayList<>();

                    for (CustomerOrSupplierSearchItem dataItem : dataListAllItems) {
                        if (dataItem.getName().toLowerCase().startsWith(searchStrLowerCase)) {
                            matchValues.add(dataItem);
                        }
                    }

                    results.values = matchValues;
                    results.count = matchValues.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                dataList = (ArrayList<CustomerOrSupplierSearchItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }
} 