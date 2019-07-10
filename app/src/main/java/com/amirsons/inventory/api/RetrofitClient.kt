package com.amirsons.inventory.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val BASE_URL = "https://api.myjson.com/bins/"
    private var api: RetrofitApi? = null

    val instance: RetrofitApi
        get() {

            if (api == null) {

                val gson = GsonBuilder().setLenient().create()

                val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()

                api = retrofit.create(RetrofitApi::class.java)
            }

            return api!!
        }
}
