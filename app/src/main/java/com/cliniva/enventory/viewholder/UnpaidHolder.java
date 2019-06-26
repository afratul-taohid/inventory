package com.cliniva.enventory.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cliniva.enventory.R;

public class UnpaidHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView price;
    public TextView date;

    public UnpaidHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.tv_company_name);
        price = itemView.findViewById(R.id.tv_price);
        date = itemView.findViewById(R.id.tv_date);
    }
}
