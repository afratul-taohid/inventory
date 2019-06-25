package com.cliniva.enventory.ui.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.cliniva.enventory.R;
import com.cliniva.enventory.adapter.TabsAdapter;
import com.cliniva.enventory.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private TabsAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        adapter = new TabsAdapter(getChildFragmentManager());
        adapter.addFragment(UnpaidFragment.getInstance(), "Unpaid");
        adapter.addFragment(AllFragment.getInstance(), "All");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
