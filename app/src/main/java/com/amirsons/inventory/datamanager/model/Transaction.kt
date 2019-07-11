package com.amirsons.inventory.datamanager.model

import com.google.firebase.database.Exclude

/**
 * Created by Taohid on 07, July, 2019
 * Email: taohid32@gmail.com
 */

data class Transaction(
        var totalPrice: Int? = null,
        var date: String? = null,
        var paymentType: String? = null,
        var paid: Int? = null,
        var due: Int? = null,
        val products: ArrayList<ProductCart> = ArrayList(),
        val extraCost: ArrayList<ExtraCost> = ArrayList(),
        var note: String? = null)

data class ProductCart(
        @get:Exclude var product: Product? = null,
        @get:Exclude var isUpdatePrice: Boolean = false,
        var productId: String? = null,
        var sellingPrice: Int = 0,
        var quantity: Int = 0)

data class ExtraCost(
        var title: String? = null,
        var amount: Int? = null)