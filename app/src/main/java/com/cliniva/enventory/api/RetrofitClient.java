package com.cliniva.enventory.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static RetrofitApi getInstance(){

        Gson gson = new GsonBuilder().setLenient().create();
        if (retrofit == null){
            String BASE_URL = "https://api.myjson.com/bins/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(RetrofitApi.class);
    }
}
