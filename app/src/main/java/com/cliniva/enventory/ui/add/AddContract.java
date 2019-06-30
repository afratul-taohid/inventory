package com.cliniva.enventory.ui.add;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import com.cliniva.enventory.model.AddProduct;
import com.cliniva.enventory.model.Product;
import com.cliniva.enventory.ui.base.BasePresenter;
import com.cliniva.enventory.ui.base.BaseView;

import java.util.List;

public class AddContract {
    interface View extends BaseView {
        void setListToView(List<AddProduct> productList);
    }

    interface Presenter extends BasePresenter {
        void onLoadList();
    }
}