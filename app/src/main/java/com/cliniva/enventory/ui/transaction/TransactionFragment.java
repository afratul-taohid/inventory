package com.cliniva.enventory.ui.transaction;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.R;
import com.cliniva.enventory.adapter.TabsAdapter;
import com.cliniva.enventory.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionFragment extends BaseFragment {


    public TransactionFragment() {
        // Required empty public constructor
    }

    public static TransactionFragment getInstance(){
        TransactionFragment fragment = new TransactionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_transaction;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);

        TabsAdapter adapter = new TabsAdapter(getChildFragmentManager());
        adapter.addFragment(UnpaidFragment.getInstance(), "Unpaid");
        adapter.addFragment(AllFragment.getInstance(), "All");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
