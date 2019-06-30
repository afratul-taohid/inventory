package com.cliniva.enventory.ui.sales;

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.ui.base.BasePresenter;
import com.cliniva.enventory.ui.base.BaseView;

import java.util.List;

public class SalesContract {

    interface View extends BaseView {
        void setListToView(List<Product> productList);
    }

    interface Presenter extends BasePresenter {
        void onLoadList();
    }
}
