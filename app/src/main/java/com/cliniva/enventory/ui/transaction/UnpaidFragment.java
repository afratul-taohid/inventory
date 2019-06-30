package com.cliniva.enventory.ui.transaction;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cliniva.enventory.R;
import com.cliniva.enventory.adapter.RecyclerViewAdapter;
import com.cliniva.enventory.adapter.base.BaseRecyclerClickListener;
import com.cliniva.enventory.adapter.base.BaseRecyclerViewHolder;
import com.cliniva.enventory.model.Transaction;
import com.cliniva.enventory.ui.add.AddActivity;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.utils.IntentUtils;
import com.cliniva.enventory.viewholder.UnpaidHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnpaidFragment extends BaseFragment implements TransactionContract.View, View.OnClickListener, BaseRecyclerClickListener<Transaction> {

    private TransactionContract.Presenter mTransactionPresenter;
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
        mTransactionPresenter = new TransactionPresenter(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_unpaid;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.rv_unpaid_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mTransactionPresenter.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mTransactionPresenter.onLoad();
        Log.d(TAG, "onViewCreated: ");

        FloatingActionButton fab = view.findViewById(R.id.btn_fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void setList(List<Transaction> unpaidList) {
        RecyclerViewAdapter<Transaction, BaseRecyclerClickListener<Transaction>> unpaidAdapter = new RecyclerViewAdapter<Transaction, BaseRecyclerClickListener<Transaction>>(unpaidList) {
            @NonNull
            @Override
            public BaseRecyclerViewHolder<Transaction, BaseRecyclerClickListener<Transaction>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mTransactionPresenter.getContext()), R.layout.item_unpaid, parent, false);
                return new UnpaidHolder(binding);
            }
        };
        unpaidAdapter.setListener(this);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mTransactionPresenter.getContext(), message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemClickListener(Transaction item, int position) {

    }
}
