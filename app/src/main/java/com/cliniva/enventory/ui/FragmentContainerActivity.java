package com.cliniva.enventory.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cliniva.enventory.R;
import com.cliniva.enventory.ui.base.BaseActivity;
import com.cliniva.enventory.ui.customer.CustomerFragment;
import com.cliniva.enventory.ui.main.MainActivity;
import com.cliniva.enventory.ui.supplier.SupplierFragment;
import com.cliniva.enventory.ui.transaction.TransactionFragment;
import com.cliniva.enventory.utils.IntentUtils;

public class FragmentContainerActivity extends BaseActivity {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_fragment_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int fragmentType = getIntent().getExtras().getInt("FRAGMENT_TYPE_EXTRA", 0);
        onFragmentTransaction(fragmentType);
    }

    private void onFragmentTransaction(int fragmentType){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (fragmentType){
            case 0:
                transaction.replace(R.id.fl_fragment_container, TransactionFragment.getInstance()).commit();
                break;
            case 1:
                transaction.replace(R.id.fl_fragment_container, CustomerFragment.getInstance()).commit();
                break;
            case 2:
                transaction.replace(R.id.fl_fragment_container, SupplierFragment.getInstance()).commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putInt("TRANSACTION_KEY", 0);
        IntentUtils.getInstance().onActivityIntentWithExtras(this, MainActivity.class, bundle);
    }
}
