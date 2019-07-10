package com.amirsons.inventory.ui.activity

import  android.os.Bundle
import androidx.fragment.app.Fragment
import com.amirsons.inventory.R
import com.amirsons.inventory.ui.base.BaseActivity
import com.amirsons.inventory.ui.fragment.home.HomeFragment
import com.amirsons.inventory.ui.fragment.inventory.InventoryFragment
import com.amirsons.inventory.ui.fragment.more.MoreFragment
import com.amirsons.inventory.ui.fragment.sales.SalesFragment
import com.amirsons.inventory.ui.fragment.supplies.SuppliesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var activeBottomFragment: Fragment? = null

    override val contentLayout: Int
        get() = R.layout.activity_main

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {

        when (it.itemId) {

            R.id.menu_home -> activeBottomFragment = addFabFragment(HomeFragment.instance, activeBottomFragment, R.id.fragment_container)

            R.id.menu_sales -> activeBottomFragment = addFabFragment(SalesFragment.instance, activeBottomFragment, R.id.fragment_container)

            R.id.menu_inventory -> activeBottomFragment = addFabFragment(InventoryFragment.instance, activeBottomFragment, R.id.fragment_container)

            R.id.menu_supplies -> activeBottomFragment = addFabFragment(SuppliesFragment.instance, activeBottomFragment, R.id.fragment_container)

            R.id.menu_more -> activeBottomFragment = addFabFragment(MoreFragment.instance, activeBottomFragment, R.id.fragment_container)
        }

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        bottom_navigation.selectedItemId = R.id.menu_home
    }
}
