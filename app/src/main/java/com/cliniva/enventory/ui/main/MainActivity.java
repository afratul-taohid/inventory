package com.cliniva.enventory.ui.main;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import com.cliniva.enventory.R;
import com.cliniva.enventory.ui.base.BaseActivity;
import com.cliniva.enventory.ui.customer.CustomerFragment;
import com.cliniva.enventory.ui.home.HomeFragment;
import com.cliniva.enventory.ui.inventory.InventoryFragment;
import com.cliniva.enventory.ui.more.MoreFragment;
import com.cliniva.enventory.ui.sales.SalesFragment;
import com.cliniva.enventory.ui.supplier.SupplierFragment;
import com.cliniva.enventory.ui.supplies.SuppliesFragment;

public class MainActivity extends BaseActivity {

    private BottomNavigationView mBottomNavView;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        mBottomNavView = findViewById(R.id.bottom_navigation);
        mBottomNavView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (bundle != null){
            onSwitchFragment(bundle.getInt("TRANSACTION_KEY"));
        } else {
            onFragmentTransaction(HomeFragment.getInstance());
        }
    }

    private void onSwitchFragment(int keyCode){
        switch (keyCode){
            case 0:
                onFragmentTransaction(MoreFragment.getInstance());
                mBottomNavView.setSelectedItemId(R.id.menu_more);
                break;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                onFragmentTransaction(HomeFragment.getInstance());
                return true;
            case R.id.menu_sales:
                onFragmentTransaction(SalesFragment.getInstance());
                return true;
            case R.id.menu_inventory:
                onFragmentTransaction(InventoryFragment.getInstance());
                return true;
            case R.id.menu_supplies:
                onFragmentTransaction(SuppliesFragment.getInstance());
                return true;
            case R.id.menu_more:
                onFragmentTransaction(MoreFragment.getInstance());
                return true;
        }
        return false;
    };

    private void onFragmentTransaction(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }
}
