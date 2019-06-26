package com.cliniva.enventory.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.viewholder.UnpaidHolder;
import com.cliniva.enventory.model.Transaction;
import com.cliniva.enventory.R;

import java.util.List;

public class CompanyListDataAdapter extends RecyclerView.Adapter<UnpaidHolder> {

    private List<Transaction> list;
    private Context context;


    public CompanyListDataAdapter(List<Transaction> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UnpaidHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_unpaid,viewGroup,false);
        return new UnpaidHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnpaidHolder unpaidHolder, int i) {

        Transaction currentdata = list.get(i);

        unpaidHolder.name.setText(currentdata.getName());
        unpaidHolder.price.setText(currentdata.getPrice());
        unpaidHolder.date.setText(currentdata.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
