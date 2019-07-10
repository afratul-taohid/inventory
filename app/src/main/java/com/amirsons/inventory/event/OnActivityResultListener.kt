package com.amirsons.inventory.event

import android.content.Intent

interface OnActivityResultListener {
    fun onResultSuccess(resultCode: Int, data: Intent?)
    fun onResultError(resultCode: Int, data: Intent?)
}