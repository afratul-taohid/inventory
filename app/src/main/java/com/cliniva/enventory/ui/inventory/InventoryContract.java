package com.cliniva.enventory.ui.inventory;

import com.cliniva.enventory.model.Brand;
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.ui.base.BasePresenter;
import com.cliniva.enventory.ui.base.BaseView;

import java.util.List;

public class InventoryContract {

    interface View extends BaseView {
        void setBrandListToView(List<Brand> brandList);
        void setProductListToView(List<Product> productList);
    }

    interface Presenter extends BasePresenter {
        void onLoadList();
    }
}
