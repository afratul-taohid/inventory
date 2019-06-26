package com.cliniva.enventory.model;

public class AddProduct {

    private String name;
    private String category;
    private String subCategory;
    private int unit;
    private float unitPrice;
    private float total;

    public AddProduct(String name, String category, String subCategory, int unit, float unitPrice, float total) {
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
