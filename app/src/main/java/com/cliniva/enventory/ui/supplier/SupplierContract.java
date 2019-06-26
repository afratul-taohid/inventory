package com.cliniva.enventory.ui.supplier;

import com.cliniva.enventory.model.Customer;
import com.cliniva.enventory.ui.base.BasePresenter;
import com.cliniva.enventory.ui.base.BaseView;

import java.util.List;

public class SupplierContract {
    interface View extends BaseView {
        void setListToView(List<Customer> supplierList);
    }
    interface Presenter extends BasePresenter {
        void onLoadList();
    }
}
