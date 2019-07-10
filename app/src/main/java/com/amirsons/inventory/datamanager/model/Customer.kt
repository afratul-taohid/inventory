package com.amirsons.inventory.datamanager.model

/**
 * Created by Taohid on 07, July, 2019
 * Email: taohid32@gmail.com
 */

data class Customer (var name: String? = null,
                     var receivableAmount: Int = 0,
                     var mobile: String? = null,
                     var address: String? = null,
                     var lastInvoiceDate: String? = null)
