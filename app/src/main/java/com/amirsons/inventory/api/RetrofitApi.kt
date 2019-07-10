package com.amirsons.inventory.api

import com.amirsons.inventory.datamanager.model.Transaction

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @get:GET("6krl8")
    val dataSet: Call<ArrayList<Transaction>>
}
