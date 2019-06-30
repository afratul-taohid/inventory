package com.cliniva.enventory.ui.transaction;

import com.cliniva.enventory.model.Transaction;
import com.cliniva.enventory.ui.base.BasePresenter;
import com.cliniva.enventory.ui.base.BaseView;

import java.util.List;

public class TransactionContract {

    interface View extends BaseView {
        void setList(List<Transaction> dataList);
        void showToast(String message);
    }

    interface Presenter extends BasePresenter {
        void onLoad();
    }
}
