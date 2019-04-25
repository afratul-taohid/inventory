package com.cliniva.enventory.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.viewholder.CompanyListViewHolder;
import com.cliniva.enventory.listdata.OurDataSet;
import com.cliniva.enventory.R;

import java.util.List;

public class CompanyListDataAdapter extends RecyclerView.Adapter<CompanyListViewHolder> {

    private List<OurDataSet> list;
    private Context context;


    public CompanyListDataAdapter(List<OurDataSet> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CompanyListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_company_view,viewGroup,false);


        return new CompanyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyListViewHolder companyListViewHolder, int i) {

        OurDataSet currentdata = list.get(i);

        companyListViewHolder.name.setText(currentdata.getName());
        companyListViewHolder.price.setText(currentdata.getPrice());
        companyListViewHolder.date.setText(currentdata.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
