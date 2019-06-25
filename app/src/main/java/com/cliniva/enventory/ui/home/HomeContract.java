package com.cliniva.enventory.ui.home;

/* Created by Imran Khan on 15-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import com.cliniva.enventory.model.Transaction;
import com.cliniva.enventory.ui.base.BasePresenter;
import com.cliniva.enventory.ui.base.BaseView;

import java.util.List;

public class HomeContract {
    interface View extends BaseView {
        void setList(List<Transaction> dataList);
    }

    interface Presenter extends BasePresenter {
        void onLoad();
    }
}
