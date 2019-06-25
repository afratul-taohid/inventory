package com.cliniva.enventory.model;

public class AvailableProductList {

    private String product_name;
    private String product_type;
    private String product_weight;


    public AvailableProductList(String product_name, String product_type, String product_weight) {
        this.product_name = product_name;
        this.product_type = product_type;
        this.product_weight = product_weight;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(String product_weight) {
        this.product_weight = product_weight;
    }
}
