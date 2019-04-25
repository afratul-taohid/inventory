package com.cliniva.enventory.retrofit;

import com.cliniva.enventory.listdata.OurDataSet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OurRetrofit {

    @GET("6krl8")
    Call<List<OurDataSet>> getDataSet();

    @GET("getProductDetails")
    Call<List<OurDataSet>> getProductDetails();
}
