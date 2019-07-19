package com.amirsons.inventory.app


/**
 * Created by Taohid on 13, July, 2019
 * Email: taohid32@gmail.com
 */
object Constant {

    // this const is string cause firebase query only accept string/double/boolean
    const val TRANSACTION_SELL = "0"
    const val TRANSACTION_BUY = "1"

    const val TRANSACTION_PAYMENT_CASH = 0
    const val TRANSACTION_PAYMENT_BANK = 1
    const val TRANSACTION_PAYMENT_CHEQUE = 2
}