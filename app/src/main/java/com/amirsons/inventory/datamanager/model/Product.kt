package com.amirsons.inventory.datamanager.model

import com.google.firebase.database.Exclude

/**
 * Created by Taohid on 07, July, 2019
 * Email: taohid32@gmail.com
 */

data class Product(var productId: String? = null,
        @get:Exclude var brand: String? = null,
        var category: String? = null,
        var weight: String? = null,
        var size: String? = null,
        var retailPrice: Int? = 0,
        var availableStock: Int = 0,
        var lastSupply: String? = null)

data class BrandProduct(val brand: String, val productList: ArrayList<Product>){
    var isSelected = false
}

data class RecentSaleProduct(var brand: String,  var category: String){
    var todayTotalSale: Int = 0
    var availableStock: Int = 0
    var productId: String? = null
    var weight: String? = null
}