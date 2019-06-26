package com.cliniva.enventory.model;

import android.databinding.BindingAdapter;

public class Customer {
    private String name;
    private String amount;
    private String lastInvoiceDate;

    public Customer(String name, String amount, String lastInvoiceDate) {
        this.name = name;
        this.amount = amount;
        this.lastInvoiceDate = lastInvoiceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLastInvoiceDate() {
        return lastInvoiceDate;
    }

    public void setLastInvoiceDate(String lastInvoiceDate) {
        this.lastInvoiceDate = lastInvoiceDate;
    }
}
