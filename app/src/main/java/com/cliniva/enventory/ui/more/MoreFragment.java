package com.cliniva.enventory.ui.more;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliniva.enventory.R;
import com.cliniva.enventory.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment implements MoreContract.View {

    private MoreContract.Presenter mMorePresenter;

    public MoreFragment() {
        // Required empty public constructor
    }

    public static MoreFragment getInstance(){
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_more;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMorePresenter = new MorePresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view, mMorePresenter.getContext().getString(R.string.action_more), false);
    }
}