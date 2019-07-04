package com.cliniva.enventory.ui.more;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.cliniva.enventory.R;
import com.cliniva.enventory.ui.FragmentContainerActivity;
import com.cliniva.enventory.ui.base.BaseFragment;
import com.cliniva.enventory.utils.IntentUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment implements MoreContract.View, View.OnClickListener {

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

        ImageView btnEventTransaction = view.findViewById(R.id.btn_event_transaction);
        ImageView btnEventCustomer = view.findViewById(R.id.btn_event_customer);
        ImageView btnEventSupplier = view.findViewById(R.id.btn_event_supplier);

        btnEventTransaction.setOnClickListener(this);
        btnEventCustomer.setOnClickListener(this);
        btnEventSupplier.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_event_transaction:
                onNavigation(0);
                break;
            case R.id.btn_event_customer:
                onNavigation(1);
                break;
            case R.id.btn_event_supplier:
                onNavigation(2);
                break;
        }
    }

    private void onNavigation(int extra){
        Bundle bundle = new Bundle();
        bundle.putInt("FRAGMENT_TYPE_EXTRA", extra);
        IntentUtils.getInstance().onActivityIntentWithExtras(mActivity, FragmentContainerActivity.class, bundle);
    }
}
