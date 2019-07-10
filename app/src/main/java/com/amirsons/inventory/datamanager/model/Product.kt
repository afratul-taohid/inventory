package com.amirsons.inventory.datamanager.model

import com.google.firebase.database.Exclude

/**
 * Created by Taohid on 07, July, 2019
 * Email: taohid32@gmail.com
 */

data class Product(
        @get:Exclude var brand: String? = null,
        var category: String? = null,
        var weight: String? = null,
        var size: String? = null,
        var retailPrice: String? = null,
        var availableStock: Int = 0,
        var lastSupply: String? = null)

data class BrandProduct(val brand: String, val productList: ArrayList<Product>){
    var isSelected = false
}