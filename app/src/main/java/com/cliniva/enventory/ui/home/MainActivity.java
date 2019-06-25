package com.cliniva.enventory.ui.home;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import com.cliniva.enventory.R;
import com.cliniva.enventory.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNavigationView mBottomNavView = findViewById(R.id.bottom_navigation);
        mBottomNavView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        onFragmentTransaction(HomeFragment.getInstance());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                onFragmentTransaction(HomeFragment.getInstance());
                return true;
            case R.id.menu_product:
                return true;
            case R.id.menu_customer:
                return true;
            case R.id.menu_suppliers:
                return true;
            case R.id.menu_more:
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
