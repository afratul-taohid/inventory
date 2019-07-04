package com.cliniva.enventory.ui.home;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.resources.TextAppearance;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.TextView;

import com.cliniva.enventory.R;
import com.cliniva.enventory.adapter.TabsAdapter;
import com.cliniva.enventory.model.Transaction;
import com.cliniva.enventory.ui.add.AddActivity;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.ui.transaction.AllFragment;
import com.cliniva.enventory.ui.transaction.UnpaidFragment;
import com.cliniva.enventory.utils.IntentUtils;
import com.cliniva.enventory.utils.string.SpanSection;
import com.cliniva.enventory.utils.string.StringSpanBuilder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, View.OnClickListener {

    private HomeContract.Presenter mHomePresenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance(){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomePresenter = new HomePresenter(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view, "Dashboard", false);

        TextView tvTotalSale = view.findViewById(R.id.tv_total_sale);
        TextView tvTotalSupply = view.findViewById(R.id.tv_total_supply);
        TextView tvPaid = view.findViewById(R.id.tv_paid);
        TextView tvPayable = view.findViewById(R.id.tv_payable);

        // total sale view
        StringSpanBuilder saleSpanBuilder = StringSpanBuilder.getInstance();
        SpanSection spanSection = SpanSection.getInstance("Total sale")
                        .setTypeface(Typeface.BOLD)
                        .setTextSize(SpanSection.TEXT_SIZE_NORMAL)
                        .setTextColor(Color.parseColor("#B4B4B4"));
        saleSpanBuilder.append(spanSection);

        saleSpanBuilder.appendNewLine();
        saleSpanBuilder.appendNewLine();

        SpanSection tkSymbolSection = SpanSection.getInstance("৳ ")
                .setTextSize(SpanSection.TEXT_SIZE_MEDIUM);
        saleSpanBuilder.append(tkSymbolSection);

        SpanSection amountSection = SpanSection.getInstance("5000")
                        .setTextColor(Color.parseColor("#3CD064"))
                        .setTextSize(SpanSection.TEXT_SIZE_LARGE);
        saleSpanBuilder.append(amountSection);
        saleSpanBuilder.buildWithTextView(tvTotalSale);

        // total supply view
        StringSpanBuilder supplySpanBuilder = StringSpanBuilder.getInstance();
        spanSection.setText("Total supply");
        supplySpanBuilder.append(spanSection);

        supplySpanBuilder.appendNewLine();
        supplySpanBuilder.appendNewLine();

        supplySpanBuilder.append(tkSymbolSection);

        amountSection.setText("10000");
        amountSection.setTextColor(Color.parseColor("#17DBFF"));
        supplySpanBuilder.append(amountSection);
        supplySpanBuilder.buildWithTextView(tvTotalSupply);

        // paid view
        StringSpanBuilder paidSpanBuilder = StringSpanBuilder.getInstance();
        spanSection.setText("Paid");
        paidSpanBuilder.append(spanSection);

        paidSpanBuilder.appendNewLine();
        paidSpanBuilder.appendNewLine();

        paidSpanBuilder.append(tkSymbolSection);

        amountSection.setTextColor(Color.parseColor("#FFC069"));
        amountSection.setText("3500");
        paidSpanBuilder.append(amountSection);
        paidSpanBuilder.buildWithTextView(tvPaid);

        // payable view
        StringSpanBuilder payableSpanBuilder = StringSpanBuilder.getInstance();
        spanSection.setText("Payable");
        payableSpanBuilder.append(spanSection);

        payableSpanBuilder.appendNewLine();
        payableSpanBuilder.appendNewLine();

        payableSpanBuilder.append(tkSymbolSection);

        amountSection.setText("3000");
        amountSection.setTextColor(Color.parseColor("#FFC069"));
        payableSpanBuilder.append(amountSection);
        payableSpanBuilder.buildWithTextView(tvPayable);

        FloatingActionButton fab = view.findViewById(R.id.fab_edit);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_edit:
                IntentUtils.getInstance()
                        .onActivityIntentWithoutExtras(getActivity(), AddActivity.class);
                break;
        }
    }

    @Override
    public void setList(List<Transaction> dataList) {

    }
}
