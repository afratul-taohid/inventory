package com.cliniva.enventory.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cliniva.enventory.R;
import com.cliniva.enventory.ui.base.BaseActivity;
import com.cliniva.enventory.ui.transaction.TransactionFragment;

public class FragmentContainerActivity extends BaseActivity {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_fragment_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int fragmentType = getIntent().getIntExtra("FRAGMENT_TYPE_EXTRA", 0);
        onFragmentTransaction(fragmentType);
    }

    private void onFragmentTransaction(int fragmentType){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (fragmentType){
            case 0:
                transaction.replace(R.id.fl_fragment_container, TransactionFragment.getInstance()).commit();
                break;
        }
    }
}
