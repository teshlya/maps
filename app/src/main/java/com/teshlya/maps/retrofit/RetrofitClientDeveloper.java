package com.teshlya.maps.retrofit;

import com.teshlya.maps.data.QueryParameters;
import com.teshlya.maps.data.ResultAddress;
import com.teshlya.maps.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClientDeveloper extends RetrofitClient{

    public static RetrofitClientDeveloper getInstance() {
        return new RetrofitClientDeveloper();
    }

    public void getAddress(QueryParameters parameters, final OnFoundAddressCallback callback) {
        getCall(parameters)
                .enqueue(new Callback<ResultAddress>() {
                    @Override
                    public void onResponse(Call<ResultAddress> call, Response<ResultAddress> response) {
                        ResultAddress resultAddress = response.body();
                        if (resultAddress == null || resultAddress.getAddress() == null) {
                            resultAddress = new ResultAddress("Not found");
                        }
                        callback.onFound(resultAddress.getAddress());
                    }

                    @Override
                    public void onFailure(Call<ResultAddress> call, Throwable t) {
                        callback.onFound((new ResultAddress("Not found")).getAddress());
                    }
                });
    }

    private Call<ResultAddress> getCall(QueryParameters parameters) {
        return getRetrofitClient(Constants.BASE_URL_DEVELOPER)
                .getAddressFromDeveloper(parameters.getKey(),
                        parameters.getLat(),
                        parameters.getLon(),
                        "json");
    }
}
