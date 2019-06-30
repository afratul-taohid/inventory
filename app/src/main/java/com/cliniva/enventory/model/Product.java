package com.cliniva.enventory.model;

/* Created by Imran Khan on 20-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

public class Product {

    private String category;
    private String subCategory;
    private String unitAmount;
    private String size;
    private String lastSupply;

    public Product(String category, String subCategory, String unitAmount, String size, String lastSupply) {
        this.category = category;
        this.subCategory = subCategory;
        this.unitAmount = unitAmount;
        this.size = size;
        this.lastSupply = lastSupply;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(String unitAmount) {
        this.unitAmount = unitAmount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLastSupply() {
        return lastSupply;
    }

    public void setLastSupply(String lastSupply) {
        this.lastSupply = lastSupply;
    }
}
