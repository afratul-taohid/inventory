package com.amirsons.inventory.datamanager.model

import com.google.firebase.database.Exclude

/**
 * Created by Taohid on 07, July, 2019
 * Email: taohid32@gmail.com
 */

data class Transaction(
        @get:Exclude var date: String? = null,
        var customerOrSupplierId: String? = null,
        var paymentType: Int? = null,
        var transactionType: String? = null,
        var totalPrice: Int = 0,
        var paid: Int = 0,
        var due: Int = 0,
        var change: Int = 0,
        var chequeOrAcNo: String? = null,
        var note: String? = null,
        val products: ArrayList<ProductCart> = ArrayList(),
        val extraCost: ExtraCost = ExtraCost(),
        @get:Exclude var customerOrSupplierPreviousAmount: Int = 0)

data class ProductCart(var productId: String? = null) {
    @get:Exclude var product: Product? = null
    @get:Exclude var isUpdatePrice: Boolean = false
    var brand: String? = null
    var unitPrice: Int = 0
    var quantity: Int = 0
}

data class ExtraCost(
        var title: String? = null,
        var amount: Int = 0)

data class TransactionHistory(
        var id: String? = null,
        var date: String? = null,
        var totalPrice: Int? = 0,
        var isPaid: Boolean = true,
        var customerName: String? = null)

class TransactionSummery {
    var totalSell: Int = 0
    var totalSupply: Int = 0
    var totalDue: Int = 0
    var totalPayable: Int = 0
}