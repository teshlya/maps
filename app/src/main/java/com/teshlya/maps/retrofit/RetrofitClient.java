package com.teshlya.maps.retrofit;

import com.teshlya.maps.api.APIService;
import com.teshlya.maps.data.QueryParameters;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitClient {
    protected APIService getRetrofitClient(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(APIService.class);
    }

    public abstract void getAddress(QueryParameters parameters, final OnFoundAddressCallback callback);
}
