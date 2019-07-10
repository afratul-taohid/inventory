package com.amirsons.inventory.datamanager.model

/**
 * Created by Taohid on 07, July, 2019
 * Email: taohid32@gmail.com
 */

data class AddProduct (
        var name: String? = null,
        var category: String? = null,
        var subCategory: String? = null,
        var unit: Int? = null,
        var unitPrice: Float? = null,
        var total: Float? = null)
