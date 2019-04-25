package com.cliniva.enventory.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cliniva.enventory.adapter.CompanyListDataAdapter;
import com.cliniva.enventory.listdata.OurDataSet;
import com.cliniva.enventory.R;
import com.cliniva.enventory.retrofit.RetrofitApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rv_data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitApi.getInstance().getDataSet().enqueue(new Callback<List<OurDataSet>>() {
            @Override
            public void onResponse(Call<List<OurDataSet>> call, Response<List<OurDataSet>> response) {
                ShowIt(response.body());
            }

            @Override
            public void onFailure(Call<List<OurDataSet>> call, Throwable t) {

            }
        });


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.btn_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SellBuyActivity.class);
                startActivity(intent);
            }
        });
    }


    private void ShowIt(List<OurDataSet> body) {

        CompanyListDataAdapter companyListDataAdapter = new CompanyListDataAdapter(body,getApplicationContext());

        recyclerView.setAdapter(companyListDataAdapter);
    }
}
