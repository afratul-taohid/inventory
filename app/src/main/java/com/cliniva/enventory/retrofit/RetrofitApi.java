package com.cliniva.enventory.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    private static Retrofit retrofitApi = null;
    private static OurRetrofit ourRetrofit = null;

    private static String BASEURL = "https://api.myjson.com/bins/";
    private static String BASEURL1 = "efljaslkdjaks";

    public static OurRetrofit getInstance(){

        if (retrofitApi == null){

             retrofitApi = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

             ourRetrofit = retrofitApi.create(OurRetrofit.class);
        }

        return ourRetrofit;
    }



}
