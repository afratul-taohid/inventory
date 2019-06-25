package com.cliniva.enventory.api;

import com.cliniva.enventory.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("6krl8")
    Call<List<Transaction>> getDataSet();
}
