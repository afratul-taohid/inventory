package com.cliniva.enventory.ui.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cliniva.enventory.R;
import com.cliniva.enventory.adapter.CompanyListDataAdapter;
import com.cliniva.enventory.model.Transaction;
import com.cliniva.enventory.ui.add.AddActivity;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.utils.IntentUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnpaidFragment extends BaseFragment implements HomeContract.View, View.OnClickListener {

    private HomeContract.Presenter mMainPresenter;
    private RecyclerView mRecyclerView;
    private static final String TAG = "UnpaidFragment";

    public UnpaidFragment() {
        // Required empty public constructor
    }

    public static UnpaidFragment getInstance(){
        UnpaidFragment fragment = new UnpaidFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainPresenter = new HomePresenter(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_unpaid;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.rv_data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mMainPresenter.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mMainPresenter.onLoad();
        Log.d(TAG, "onViewCreated: ");

        FloatingActionButton fab = view.findViewById(R.id.btn_fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void setList(List<Transaction> dataList) {
        mRecyclerView.setAdapter(new CompanyListDataAdapter(dataList, mMainPresenter.getContext()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fab:
                IntentUtils.getInstance()
                        .onActivityIntentWithoutExtras(getActivity(), AddActivity.class);
                break;
        }
    }
}
