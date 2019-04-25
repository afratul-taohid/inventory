package com.cliniva.enventory.listdata;

public class AddProductList {

    private String add_product_name;
    private String add_product_type;
    private String add_product_weight;
    private String add_product_unit;
    private String add_product_x;
    private String add_product_price_per_unit;
    private String add_product_total_price;


    public AddProductList(String add_product_name, String add_product_type, String add_product_weight, String add_product_unit, String add_product_x, String add_product_price_per_unit, String add_product_total_price) {
        this.add_product_name = add_product_name;
        this.add_product_type = add_product_type;
        this.add_product_weight = add_product_weight;
        this.add_product_unit = add_product_unit;
        this.add_product_x = add_product_x;
        this.add_product_price_per_unit = add_product_price_per_unit;
        this.add_product_total_price = add_product_total_price;
    }


    public String getAdd_product_name() {
        return add_product_name;
    }

    public void setAdd_product_name(String add_product_name) {
        this.add_product_name = add_product_name;
    }

    public String getAdd_product_type() {
        return add_product_type;
    }

    public void setAdd_product_type(String add_product_type) {
        this.add_product_type = add_product_type;
    }

    public String getAdd_product_weight() {
        return add_product_weight;
    }

    public void setAdd_product_weight(String add_product_weight) {
        this.add_product_weight = add_product_weight;
    }

    public String getAdd_product_unit() {
        return add_product_unit;
    }

    public void setAdd_product_unit(String add_product_unit) {
        this.add_product_unit = add_product_unit;
    }

    public String getAdd_product_x() {
        return add_product_x;
    }

    public void setAdd_product_x(String add_product_x) {
        this.add_product_x = add_product_x;
    }

    public String getAdd_product_price_per_unit() {
        return add_product_price_per_unit;
    }

    public void setAdd_product_price_per_unit(String add_product_price_per_unit) {
        this.add_product_price_per_unit = add_product_price_per_unit;
    }

    public String getAdd_product_total_price() {
        return add_product_total_price;
    }

    public void setAdd_product_total_price(String add_product_total_price) {
        this.add_product_total_price = add_product_total_price;
    }
}
