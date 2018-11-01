package com.teshlya.maps.api;

import com.teshlya.maps.utils.Constants;
import com.teshlya.maps.data.ResultAddress;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET(Constants.REVERSE_LOCATIONIQ)
    Call<ResultAddress> getAddressFromLocationiq(@Query(Constants.KEY) String key,
                                                 @Query(Constants.LAT) String lat,
                                                 @Query(Constants.LON) String lon,
                                                 @Query(Constants.FORMAT) String format);

    @GET(Constants.REVERSE_DEVELOPER)
    Call<ResultAddress> getAddressFromDeveloper(@Query(Constants.KEY) String key,
                                                 @Query(Constants.LAT) String lat,
                                                 @Query(Constants.LON) String lon,
                                                 @Query(Constants.FORMAT) String format);
}
