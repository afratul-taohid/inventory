package com.cliniva.enventory.ui.customer;

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import com.cliniva.enventory.model.Customer;
import com.cliniva.enventory.ui.base.BasePresenter;
import com.cliniva.enventory.ui.base.BaseView;

import java.util.List;

public class CustomerContract {
    interface View extends BaseView {
        void setListToView(List<Customer> customerList);
    }

    interface Presenter extends BasePresenter {
        void onLoadList();
    }
}